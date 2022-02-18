package com.example.shoppinglist.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.shoppinglist.databinding.FragmentShopItemBinding
import com.example.shoppinglist.domain.model.ShopItem

class ShopItemActivity : AppCompatActivity() {
    val viewModel by viewModels<ShopItemViewModel>()
    var shopItemId = ShopItem.UNDEFINED_ID
    private var screenMode = MODE_UNKNOWN
    private lateinit var binding: FragmentShopItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentShopItemBinding.inflate(layoutInflater).also { setContentView(it.root) }
        parseIntent()
        startScreenRightMode()
        setupCountTextWatcher()
        setupNameTextWatcher()
        viewModel.errorInputCount.observe(this) {
            if (it) binding.tilCount.error = "Count filed is empty, please enter something"
        }

        viewModel.errorInputName.observe(this) {
            if (it) binding.tilName.error = "Count filed is empty, please enter something"
        }

        viewModel.shouldCloseScreen.observe(this) {
            finish()
        }

    }

    private fun startScreenRightMode() {
        when (screenMode) {
            MODE_EDIT -> launchEditMode(shopItemId)
            MODE_ADD -> launchAddMode()
        }
    }


    private fun setupNameTextWatcher() {
        val nameTextWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.resetErrorName()
                binding.tilName.error = null
            }

            override fun afterTextChanged(s: Editable?) {}
        }
        binding.etName.addTextChangedListener(nameTextWatcher)
    }


    private fun setupCountTextWatcher() {
        val countTextWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.resetErrorCount()
                binding.tilCount.error = null
            }

            override fun afterTextChanged(s: Editable?) {}
        }
        binding.etCount.addTextChangedListener(countTextWatcher)
    }



    private fun launchAddMode() {
        binding.saveButton.setOnClickListener {
            val name = binding.etName.text?.toString()
            val count = binding.etCount.text?.toString()
            viewModel.addShopItem(name, count)
        }
    }

    private fun launchEditMode(shopItemId: Int) {
        viewModel.getShopItem(shopItemId)
        viewModel.shopItem.observe(this) {
            binding.etCount.setText(it.count.toString())
            binding.etName.setText(it.name)
        }

        binding.saveButton.setOnClickListener {
            with(binding){
                viewModel.editShopItem(etName.text?.toString(), etCount.text?.toString())
            }
        }

    }


    private fun parseIntent() {
        if (!intent.hasExtra(EXTRA_SCREEN_MODE)) {
            throw RuntimeException("Param screen mode is absent")
        }
        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)

        if (mode != MODE_ADD && mode != MODE_EDIT) {
            throw RuntimeException("Unknown screen mode")
        }
        screenMode = mode

        if (screenMode == MODE_EDIT) {
            if (!intent.hasExtra(EXTRA_SHOP_ITEM_ID)) {
                throw java.lang.RuntimeException("Param shopItem id is absent")
            }
            shopItemId = intent.getIntExtra(EXTRA_SHOP_ITEM_ID, ShopItem.UNDEFINED_ID)

        }


    }

    companion object {
        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"
        private const val MODE_UNKNOWN = ""
        private const val EXTRA_SHOP_ITEM_ID = "shop_item_id"

        fun newIntentAddItem(context: Context): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
            return intent
        }

        fun newIntentEditItem(context: Context, shopItemId: Int): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
            intent.putExtra(EXTRA_SHOP_ITEM_ID, shopItemId)
            return intent
        }
    }
}