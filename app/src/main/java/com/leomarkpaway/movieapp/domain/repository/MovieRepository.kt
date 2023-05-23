package com.leomarkpaway.movieapp.domain.repository

import com.leomarkpaway.movieapp.data.source.local.entity.Movie

interface MovieRepository {
    suspend fun addMovie(movie: List<Movie>)
    suspend fun getAllMovies() : List<Movie>
    suspend fun sortByDate() :List<Movie>
}