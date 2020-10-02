package com.showcase.cricksquad.di

import android.content.Context
import com.showcase.cricksquad.CacheGateway
import com.showcase.cricksquad.NetworkGateway
import com.showcase.cricksquad.database.DatabaseFactory
import com.showcase.cricksquad.network.NetworkFactory
import com.showcase.cricksquad.schedulers.DefaultScheduler
import com.showcase.cricksquad.schedulers.SchedulerProvider


/**
 * Top level component in the dependency graph.
 * This component is attached to the top most android framework component in use.
 * Currently, this root is attached to Activity
 * @see [PresentationRoot]
 */
class CompositionRoot(
    private val applicationContext: Context
) {

    private var networkGateway: NetworkGateway? = null
    private var scheduler: SchedulerProvider? = null
    private var cacheGateway: CacheGateway?= null

    fun getSchedulerProvider(): SchedulerProvider {
        return scheduler
            ?: DefaultScheduler()
                .also { scheduler = it }
    }

    fun getNetworkGateway(): NetworkGateway {
        return networkGateway
            ?: NetworkFactory.createGateway()
                .also { networkGateway = it }
    }

    fun getCacheGateway(): CacheGateway {
        return cacheGateway
            ?: DatabaseFactory.getGateway(applicationContext)
                .also { cacheGateway = it }
    }
}