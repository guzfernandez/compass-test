package com.guzfernandez.compasstestapp.data

import android.content.Context
import androidx.room.Room
import com.guzfernandez.compasstestapp.data.database.WebContentDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val DATABASE_NAME = "compass_db"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, WebContentDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideQuoteDao(db: WebContentDatabase) = db.getWebContentDao()
}