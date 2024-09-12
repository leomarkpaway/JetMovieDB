package com.leomarkpaway.jetmoviedb.di

import com.leomarkpaway.jetmoviedb.data.repository.MovieRepository
import com.leomarkpaway.jetmoviedb.data.repository.MovieRepositoryImpl
import com.leomarkpaway.jetmoviedb.data.source.local.database.MoviesDatabase
import com.leomarkpaway.jetmoviedb.data.source.remote.service.MovieApi
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
    fun provideMovieRepository(movieApi: MovieApi, moviesDatabase: MoviesDatabase) : MovieRepository {
        return MovieRepositoryImpl(movieApi = movieApi, moviesDatabase = moviesDatabase)
    }

}