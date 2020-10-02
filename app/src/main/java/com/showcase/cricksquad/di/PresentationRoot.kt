package com.showcase.cricksquad.di

import com.showcase.cricksquad.repository.CricketRepository
import com.showcase.cricksquad.repository.dataSource.CricketCache
import com.showcase.cricksquad.repository.dataSource.CricketRemote
import com.showcase.cricksquad.ui.HomeVmFactory

/**
 * Second level component in the dependency graph.
 * This root is synonymous to presentation layer.
 * Currently, this is attached to every fragment
 * @see [CompositionRoot]
 */
class PresentationRoot(
    private val compositionRoot: CompositionRoot,
) {

    fun homeVmFactory(): HomeVmFactory {
        return HomeVmFactory(
            compositionRoot.getSchedulerProvider(),
            getCricketRepository()
        )
    }

    private fun getCricketRepository(): CricketRepository {
        return CricketRepository(
            getCricketRemote(),
            getCricketCache()
        )
    }

    private fun getCricketRemote(): CricketRemote {
        return compositionRoot
            .getNetworkGateway()
            .getCricketRemote()
    }

    private fun getCricketCache(): CricketCache {
        return compositionRoot
            .getCacheGateway()
            .getCricketCache()
    }

}