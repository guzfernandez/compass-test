package com.guzfernandez.compasstestapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.guzfernandez.compasstestapp.data.database.dao.WebContentDao
import com.guzfernandez.compasstestapp.data.database.entities.WebContentEntity

@Database(entities = [WebContentEntity::class], version = 1)
abstract class WebContentDatabase: RoomDatabase() {

    abstract fun getWebContentDao(): WebContentDao
}