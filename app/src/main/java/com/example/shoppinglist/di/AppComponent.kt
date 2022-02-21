package com.example.shoppinglist.di

import android.app.Application
import com.example.shoppinglist.presentation.screen.MainActivity
import com.example.shoppinglist.presentation.screen.ShopItemFragment
import dagger.BindsInstance
import dagger.Component

/**
 * Created by osmanboy on 2/21/2022.
 */

@ApplicationScope
@Component(modules = [DataModule::class,ViewModelModule::class])
interface AppComponent {


    fun inject(activity: MainActivity)
    fun inject(activity: ShopItemFragment)

    @Component.Factory
    interface AppFactory {

        fun create(@BindsInstance application: Application): AppComponent
    }

}