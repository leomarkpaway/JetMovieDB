package com.leomarkpaway.movieapp.data.source.remote.service

import com.leomarkpaway.movieapp.common.Constants.API_ENDPOINT
import com.leomarkpaway.movieapp.common.Constants.METHOD_NAME
import com.leomarkpaway.movieapp.data.source.remote.dto.MoviesDTO
import com.leomarkpaway.movieapp.data.source.remote.dto.MovieItemDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(API_ENDPOINT)
    suspend fun getMovies(
        @Query(METHOD_NAME) methodName: String
    ): MoviesDTO

    @GET(API_ENDPOINT)
    suspend fun getMoviesById(
        @Query(METHOD_NAME) methodName: String,
        @Query("id") id: Int
    ): MovieItemDTO

    @GET(API_ENDPOINT)
    suspend fun toggleWatchlist(
        @Query(METHOD_NAME) methodName: String,
        @Query("id") id: Int
    ): MovieItemDTO

}