package com.leomarkpaway.jetmoviedb.di

import android.content.Context
import androidx.room.Room
import com.leomarkpaway.jetmoviedb.data.source.local.database.MoviesDatabase
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
    fun provideDatabase(@ApplicationContext appContext: Context): MoviesDatabase {
        return Room.databaseBuilder(
            appContext,
            MoviesDatabase::class.java,
            "movie_database.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideMovieDao(moviesDatabase: MoviesDatabase) = moviesDatabase.moviesDao()

    @Provides
    @Singleton
    fun provideGenreDao(moviesDatabase: MoviesDatabase) = moviesDatabase.genreDao()

}