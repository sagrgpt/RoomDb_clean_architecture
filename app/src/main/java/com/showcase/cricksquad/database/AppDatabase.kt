package com.showcase.cricksquad.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Team::class, Player::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getTeamDao(): TeamDao
}