package com.showcase.cricksquad.di

import com.showcase.cricksquad.repository.CricketRepository
import com.showcase.cricksquad.repository.dataSource.CricketCache
import com.showcase.cricksquad.repository.dataSource.CricketRemote
import com.showcase.cricksquad.ui.SquadVmFactory

/**
 * Second level component in the dependency graph.
 * This root is synonymous to presentation layer.
 * Currently, this is attached to every fragment
 * @see [CompositionRoot]
 */
class PresentationRoot(
    private val compositionRoot: CompositionRoot,
) {

    fun getSquadVmFactory(): SquadVmFactory {
        return SquadVmFactory(
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