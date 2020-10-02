package com.showcase.cricksquad.database

import android.content.Context
import androidx.room.Room
import com.showcase.cricksquad.database.room.AppDatabase

object DatabaseFactory {

    private const val dbName = "CricketSquadDb"

    fun getDatabase(applicationContext: Context): AppDatabase {
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, dbName
        ).build()
    }

}