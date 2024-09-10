package com.leomarkpaway.movieapp.di

import com.leomarkpaway.movieapp.data.repository.MovieRepository
import com.leomarkpaway.movieapp.data.repository.MovieRepositoryImpl
import com.leomarkpaway.movieapp.data.source.local.database.MoviesDatabase
import com.leomarkpaway.movieapp.data.source.remote.service.MovieApi
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