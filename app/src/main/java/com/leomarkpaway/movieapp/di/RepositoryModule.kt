package com.leomarkpaway.movieapp.di

import com.leomarkpaway.movieapp.data.repository.MovieRepositoryImpl
import com.leomarkpaway.movieapp.data.source.local.database.AppDatabase
import com.leomarkpaway.movieapp.data.source.preference.PreferenceManager
import com.leomarkpaway.movieapp.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(appDatabase: AppDatabase, preference: PreferenceManager) : MovieRepository {
        return MovieRepositoryImpl(appDatabase = appDatabase, preference = preference)
    }

}