package com.example.shoppinglist.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.shoppinglist.databinding.ActivityMainBinding
import com.example.shoppinglist.presentation.ShopListAdapter.Companion.MAX_POOL_SIZE
import com.example.shoppinglist.presentation.ShopListAdapter.Companion.VIEW_TYPE_DISABLED
import com.example.shoppinglist.presentation.ShopListAdapter.Companion.VIEW_TYPE_ENABLED

class MainActivity : AppCompatActivity() {
    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ShopListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }
        viewModel.shopList.observe(this) {
            setupRecyclerView()
        }
    }

    private fun setupRecyclerView() {
        adapter = ShopListAdapter()
        binding.rvShopList.adapter = adapter
        binding.rvShopList.recycledViewPool.setMaxRecycledViews(VIEW_TYPE_ENABLED, MAX_POOL_SIZE)
        binding.rvShopList.recycledViewPool.setMaxRecycledViews(VIEW_TYPE_DISABLED, MAX_POOL_SIZE)
    }
}