package com.showcase.cricksquad.ui.common

import androidx.annotation.UiThread
import androidx.appcompat.app.AppCompatActivity
import com.showcase.cricksquad.App
import com.showcase.cricksquad.di.CompositionRoot
import com.showcase.cricksquad.di.Injector
import com.showcase.cricksquad.di.PresentationRoot

open class BaseActivity : AppCompatActivity(){

    private var presentationRoot: PresentationRoot? = null

    @UiThread
    protected fun getInjector(): Injector {
        return Injector(getPresentationRoot())
    }

    private fun getPresentationRoot(): PresentationRoot {
        return presentationRoot
            ?: PresentationRoot(getCompositionRoot())
                .also { presentationRoot = it }
    }

    private fun getCompositionRoot(): CompositionRoot {
        return (application as App).getCompositionRoot()
    }

}