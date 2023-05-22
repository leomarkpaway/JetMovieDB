package com.leomarkpaway.movieapp.di

import android.content.Context
import androidx.room.Room
import com.leomarkpaway.movieapp.data.source.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "DataBaseName"
        ).build()
    }

    @Provides
    @Singleton
    fun provideMovieDao(database: AppDatabase) = database.movieDao()

}