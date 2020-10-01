package com.showcase.cricksquad.database

import android.app.Application
import androidx.room.Room

object DatabaseFactory {

    private const val dbName = "CricketSquadDb"

    fun getDatabase(applicationContext: Application): AppDatabase {
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, dbName
        ).build()
    }

}