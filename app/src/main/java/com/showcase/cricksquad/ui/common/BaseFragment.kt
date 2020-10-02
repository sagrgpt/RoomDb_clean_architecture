package com.showcase.cricksquad.ui.common

import androidx.annotation.UiThread
import androidx.fragment.app.Fragment
import com.showcase.cricksquad.di.CompositionRoot
import com.showcase.cricksquad.di.Injector
import com.showcase.cricksquad.di.PresentationRoot
import com.showcase.cricksquad.ui.MainActivity

open class BaseFragment : Fragment(){

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
        return (activity as MainActivity).getCompositionRoot()
    }

}