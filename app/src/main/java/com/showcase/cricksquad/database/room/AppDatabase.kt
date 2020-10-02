package com.showcase.cricksquad.database.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TeamDbo::class, PlayerDbo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getTeamDao(): TeamDao
}