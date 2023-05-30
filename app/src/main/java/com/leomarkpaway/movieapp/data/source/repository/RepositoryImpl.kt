package com.leomarkpaway.movieapp.data.source.repository

import com.leomarkpaway.movieapp.common.Constants.METHOD_GET_MOVIES
import com.leomarkpaway.movieapp.common.Constants.METHOD_GET_MOVIE_BY_ID
import com.leomarkpaway.movieapp.common.Constants.METHOD_TOGGLE_WATCHLIST
import com.leomarkpaway.movieapp.data.source.remote.dto.MovieItemDTO
import com.leomarkpaway.movieapp.data.source.remote.dto.MoviesDTO
import com.leomarkpaway.movieapp.data.source.remote.service.ApiService
import com.leomarkpaway.movieapp.domain.repository.Repository

class RepositoryImpl(private val apiService: ApiService) :  Repository {

    override suspend fun getMovies(): MoviesDTO {
        return apiService.getMovies(METHOD_GET_MOVIES)
    }

    override suspend fun toggleWatchlist(id: Int): MovieItemDTO {
        return apiService.toggleWatchlist(METHOD_TOGGLE_WATCHLIST, id)
    }

}