package com.showcase.cricksquad.di

import com.showcase.cricksquad.ui.SquadFragment


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
            is SquadFragment -> injectDependencies(client)
            else -> throw RuntimeException("Invalid view injection")
        }
    }

    private fun injectDependencies(client: SquadFragment) {
        client.viewModelFactory = presentationRoot.getSquadVmFactory()
    }
}