package com.example.shoppinglist.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.shoppinglist.databinding.ActivityShopIitemBinding

class ShopItemActivity : AppCompatActivity() {
    val viewModel by viewModels<ShopItemViewModel>()
    private lateinit var binding: ActivityShopIitemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopIitemBinding.inflate(layoutInflater).also { setContentView(it.root) }
    }


    companion object {
        const val EXTRA_SCREEN_MODE = "extra_mode"
        const val MODE_EDIT = "mode_edit"
        const val MODE_ADD = "mode_add"
        const val EXTRA_SHOP_ITEM_ID="shop_item_id"

        fun newIntentAddItem(context: Context):Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
            return intent
        }

        fun newIntentEditItem(context: Context,shopItemId:Int): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
            intent.putExtra(EXTRA_SHOP_ITEM_ID,shopItemId)
            return intent
        }
    }
}