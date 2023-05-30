package com.leomarkpaway.movieapp.domain.repository

import com.leomarkpaway.movieapp.data.source.remote.dto.MovieItemDTO
import com.leomarkpaway.movieapp.data.source.remote.dto.MoviesDTO

interface Repository {
    suspend fun getMovies(): MoviesDTO
    suspend fun toggleWatchlist(id: Int): MovieItemDTO
}