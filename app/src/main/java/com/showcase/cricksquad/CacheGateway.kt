package com.showcase.cricksquad

import com.showcase.cricksquad.repository.dataSource.CricketCache

interface CacheGateway {
    fun getCricketCache(): CricketCache
}