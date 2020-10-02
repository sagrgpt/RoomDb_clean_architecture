package com.showcase.cricksquad.database

import android.content.Context
import androidx.room.Room
import com.showcase.cricksquad.CacheGateway
import com.showcase.cricksquad.database.room.AppDatabase

object DatabaseFactory {

    private const val dbName = "CricketSquadDb"
    private var gateway: CacheGateway? = null

    fun getGateway(context: Context): CacheGateway {
        return gateway
            ?: DatabaseGateway(getDatabase(context.applicationContext))
            .also { gateway = it }
    }

    private fun getDatabase(applicationContext: Context): AppDatabase {
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, dbName
        ).build()
    }

}