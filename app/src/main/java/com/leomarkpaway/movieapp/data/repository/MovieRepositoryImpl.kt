package com.leomarkpaway.movieapp.data.repository

import com.leomarkpaway.movieapp.data.source.local.database.AppDatabase
import com.leomarkpaway.movieapp.data.source.local.entity.Movie
import com.leomarkpaway.movieapp.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val appDatabase: AppDatabase
) : MovieRepository {
    override suspend fun addMovie(movie: Movie) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllMovies(): List<Movie> {
        TODO("Not yet implemented")
    }
}