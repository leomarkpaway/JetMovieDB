package com.leomarkpaway.movieapp.data.repository

import com.leomarkpaway.movieapp.data.source.local.database.AppDatabase
import com.leomarkpaway.movieapp.data.source.local.entity.Movie
import com.leomarkpaway.movieapp.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val appDatabase: AppDatabase
) : MovieRepository {
    override suspend fun addMovie(movie: List<Movie>) {
        appDatabase.movieDao().insertAll(movie)
    }

    override suspend fun getAllMovies(): List<Movie> {
        return appDatabase.movieDao().getAllMovie()
    }
}