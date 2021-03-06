package com.example.shoppinglist.presentation.screen

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.shoppinglist.databinding.FragmentShopItemBinding
import com.example.shoppinglist.domain.model.ShopItem


class ShopItemFragment : Fragment() {

    private lateinit var onEditingFinishedListener: OnEditingFinishedListener

    private var shopItemId = ShopItem.UNDEFINED_ID
    private var screenMode = MODE_UNKNOWN

    private val shopItemViewModel by viewModels<ShopItemViewModel>()
    private lateinit var binding: FragmentShopItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParams()

    }

    override fun onAttach(context: Context) {

        super.onAttach(context)
        if (context is OnEditingFinishedListener) {
            onEditingFinishedListener = context
        } else {
            throw RuntimeException("${context.javaClass.name} must implements OnEditingFinishedListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShopItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = shopItemViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        startScreenRightMode()
        setupCountTextWatcher()
        setupNameTextWatcher()

        shopItemViewModel.shouldCloseScreen.observe(viewLifecycleOwner) {
            onEditingFinishedListener.onEditingFinished()
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
                shopItemViewModel.resetErrorName()
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
                shopItemViewModel.resetErrorCount()
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
            shopItemViewModel.addShopItem(name, count)
        }
    }

    private fun launchEditMode(shopItemId: Int) {
        shopItemViewModel.getShopItem(shopItemId)
        binding.saveButton.setOnClickListener {
            with(binding) {
                shopItemViewModel.editShopItem(etName.text?.toString(), etCount.text?.toString())
            }
        }
    }


    private fun parseParams() {
        arguments?.let {
            if (!it.containsKey(EXTRA_SCREEN_MODE)) throw RuntimeException("Param screen mode is absent")
            val mode = it.getString(EXTRA_SCREEN_MODE)

            if (mode != MODE_ADD && mode != MODE_EDIT) throw RuntimeException("Unknown screen mode")

            screenMode = mode

            if (screenMode == MODE_EDIT) {
                if (!it.containsKey(EXTRA_SHOP_ITEM_ID))
                    throw java.lang.RuntimeException("Param shopItem id is absent")

                shopItemId = it.getInt(EXTRA_SHOP_ITEM_ID, ShopItem.UNDEFINED_ID)
            }
        }


    }

    companion object {

        private const val TAG="Shop"
        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"
        private const val MODE_UNKNOWN = ""
        private const val EXTRA_SHOP_ITEM_ID = "shop_item_id"

        fun newInstanceEditItem(shopItemId: Int): ShopItemFragment {
            return ShopItemFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_SCREEN_MODE, MODE_EDIT)
                    putInt(EXTRA_SHOP_ITEM_ID, shopItemId)
                }
            }
        }

        fun newInstanceAddItem(): ShopItemFragment {
            return ShopItemFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_SCREEN_MODE, MODE_ADD)
                }
            }
        }


    }


    interface OnEditingFinishedListener {

        fun onEditingFinished()

    }


}









