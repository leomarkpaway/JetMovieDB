package com.leomarkpaway.movieapp.domain.repository

import com.leomarkpaway.movieapp.data.source.local.entity.Movie

interface MovieRepository {
    suspend fun addMovie(movie: Movie)
    suspend fun getAllMovies() : List<Movie>
}