package com.showcase.cricksquad.repository

import com.showcase.cricksquad.repository.dataSource.CricketCache

interface CacheGateway {
    fun getCricketCache(): CricketCache
}