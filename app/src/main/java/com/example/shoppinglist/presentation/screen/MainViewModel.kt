package com.example.shoppinglist.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppinglist.domain.model.ShopItem
import com.example.shoppinglist.domain.usecase.EditShopItemUseCase
import com.example.shoppinglist.domain.usecase.GetShopListUseCase
import com.example.shoppinglist.domain.usecase.RemoveShopItemUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(getShopListUseCase: GetShopListUseCase,
   private val editShopItemUseCase: EditShopItemUseCase,
   private val removeShopItemUseCase: RemoveShopItemUseCase,
) : ViewModel() {


    val shopList = getShopListUseCase.getShopList()

    fun removeShopItem(shopItem: ShopItem) {
        viewModelScope.launch(Dispatchers.IO) {
            removeShopItemUseCase.removeShopItem(shopItem)
        }
    }

    fun changeEnableState(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        viewModelScope.launch(Dispatchers.IO) {
            editShopItemUseCase.editShopItem(newItem)
        }
    }
}