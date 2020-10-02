package com.showcase.cricksquad.database

import com.showcase.cricksquad.CacheGateway
import com.showcase.cricksquad.database.room.AppDatabase
import com.showcase.cricksquad.repository.dataSource.CricketCache

class DatabaseGateway(
    private val appDatabase: AppDatabase
): CacheGateway {

    override fun getCricketCache(): CricketCache {
        return CricketDb(appDatabase.getTeamDao())
    }

}