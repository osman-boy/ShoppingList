package com.example.shoppinglist.presentation.screen

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.LEFT
import androidx.recyclerview.widget.ItemTouchHelper.RIGHT
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.shoppinglist.App
import com.example.shoppinglist.databinding.ActivityMainBinding
import com.example.shoppinglist.presentation.adapter.ShopListAdapter
import com.example.shoppinglist.presentation.adapter.ShopListAdapter.Companion.MAX_POOL_SIZE
import com.example.shoppinglist.presentation.adapter.ShopListAdapter.Companion.VIEW_TYPE_DISABLED
import com.example.shoppinglist.presentation.adapter.ShopListAdapter.Companion.VIEW_TYPE_ENABLED
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ShopItemFragment.OnEditingFinishedListener {

    @Inject
    lateinit var factory: ViewModelFactory

    private val component by lazy {
        (application as App).component
    }
    private val viewModel by viewModels<MainViewModel> { factory }
    private lateinit var binding: ActivityMainBinding
    private lateinit var shopListAdapter: ShopListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }
        setupRecyclerView()

        viewModel.shopList.observe(this) {
            shopListAdapter.submitList(it)
        }
    }

    private fun setupRecyclerView() {
        shopListAdapter = ShopListAdapter()
        with(binding.rvShopList) {
            adapter = shopListAdapter
            recycledViewPool.setMaxRecycledViews(VIEW_TYPE_ENABLED, MAX_POOL_SIZE)
            recycledViewPool.setMaxRecycledViews(VIEW_TYPE_DISABLED, MAX_POOL_SIZE)
            setupClickListener()
            setupSwapListener()
        }

    }

    private fun setupClickListener() {
        shopListAdapter.onShopItemLongClickListener = { viewModel.changeEnableState(shopItem = it) }

        shopListAdapter.onShopItemClickListener = {
            launchFragment(
                fragment = ShopItemFragment.newInstanceEditItem(it.id),
                intent = ShopItemActivity.newIntentEditItem(this, it.id),
            )
        }

        binding.buttonAddShopItem.setOnClickListener {
            launchFragment(
                fragment = ShopItemFragment.newInstanceAddItem(),
                intent = ShopItemActivity.newIntentAddItem(this),
            )

        }
    }

    private fun launchFragment(fragment: Fragment, intent: Intent) {
        binding.shopItemMainContainer?.apply {
            supportFragmentManager.popBackStack()
            supportFragmentManager.beginTransaction()
                .replace(id, fragment)
                .addToBackStack(null)
                .commit()
        } ?: startActivity(intent)
    }

    private fun setupSwapListener() {
        val callback = object : ItemTouchHelper.SimpleCallback(0, LEFT or RIGHT) {
            override fun onMove(recyc: RecyclerView, hold: ViewHolder, target: ViewHolder) = false

            override fun onSwiped(viewHolder: ViewHolder, direction: Int) {
                val shopItem = shopListAdapter.currentList[viewHolder.adapterPosition]
                viewModel.removeShopItem(shopItem)
            }
        }
        ItemTouchHelper(callback).attachToRecyclerView(binding.rvShopList)

    }

    override fun onEditingFinished() {
        onBackPressed()
    }
}