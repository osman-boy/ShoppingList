package com.example.shoppinglist.di

import androidx.lifecycle.ViewModel
import androidx.room.MapInfo
import com.example.shoppinglist.presentation.screen.MainViewModel
import com.example.shoppinglist.presentation.screen.ShopItemViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by osmanboy on 2/21/2022.
 */
@Module
interface ViewModelModule {

    @IntoMap
    @Binds
    @ViewModelKey(ShopItemViewModel::class)
    fun shopItemViewModel(viewModel: ShopItemViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(MainViewModel::class)
    fun mainViewModel(viewModel: MainViewModel): ViewModel
}