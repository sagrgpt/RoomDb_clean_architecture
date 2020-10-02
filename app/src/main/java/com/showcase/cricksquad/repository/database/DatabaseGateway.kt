package com.showcase.cricksquad.repository.database

import com.showcase.cricksquad.repository.CacheGateway
import com.showcase.cricksquad.repository.database.room.AppDatabase
import com.showcase.cricksquad.repository.dataSource.CricketCache

class DatabaseGateway(
    private val appDatabase: AppDatabase
): CacheGateway {

    override fun getCricketCache(): CricketCache {
        return CricketDb(appDatabase.getTeamDao())
    }

}