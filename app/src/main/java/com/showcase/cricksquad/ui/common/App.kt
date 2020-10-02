package com.showcase.cricksquad.ui.common

import android.app.Application
import com.showcase.cricksquad.di.CompositionRoot

class App : Application() {

    private lateinit var compositionRoot: CompositionRoot

    override fun onCreate() {
        super.onCreate()
        compositionRoot = CompositionRoot(this)
    }

    fun getCompositionRoot(): CompositionRoot {
        return compositionRoot
    }
}