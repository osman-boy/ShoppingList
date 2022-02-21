package com.example.shoppinglist

import android.app.Application
import com.example.shoppinglist.di.DaggerAppComponent

/**
 * Created by osmanboy on 2/21/2022.
 */
class App : Application() {
    val component by lazy{
        DaggerAppComponent.factory().create(this)
    }
}