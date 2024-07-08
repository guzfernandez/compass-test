package com.guzfernandez.compasstestapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.guzfernandez.compasstestapp.data.database.entities.WebContentEntity

@Dao
interface WebContentDao {

    @Query("SELECT * FROM compass_table")
    suspend fun getAllQuotes() : List<WebContentEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(quotes : List<WebContentEntity>)

    @Query("DELETE FROM compass_table")
    suspend fun deleteAllQuotes()
}