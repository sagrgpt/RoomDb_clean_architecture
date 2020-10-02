package com.showcase.cricksquad.di

import com.showcase.cricksquad.ui.HomeActivity


/**
 * Injection framework to inject public dependencies of any class.
 */
class Injector(private val presentationRoot: PresentationRoot) {

    /**
     * @param client
     *  The object which needs dependencies injected into
     */
    fun inject(client: Any) {
        when (client) {
            is HomeActivity -> injectDependencies(client)
            else -> throw RuntimeException("Invalid view injection")
        }
    }

    private fun injectDependencies(client: HomeActivity) {
        client.viewModelFactory = presentationRoot.homeVmFactory()
    }
}