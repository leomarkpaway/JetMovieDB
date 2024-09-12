package com.leomarkpaway.jetmoviedb.data.repository

import androidx.lifecycle.LiveData
import com.leomarkpaway.jetmoviedb.data.source.remote.entity.Genre
import com.leomarkpaway.jetmoviedb.data.source.remote.entity.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getNowShowing(): Flow<List<Movie>>
    suspend fun getSimilarMovies(movieId: String): Flow<List<Movie>>
    suspend fun getMyWatchlist(): LiveData<List<Movie>>
    suspend fun addToMyWatchlist(movie: Movie)
    suspend fun removeFromMyWatchlist(movie: Movie)
    suspend fun fetchAndSaveGenresToDatabase(): Flow<List<Genre>>
    suspend fun getGenres(): LiveData<List<Genre>>
    suspend fun getTrendingMovies(): Flow<List<Movie>>
    suspend fun getPopularMovies(): Flow<List<Movie>>
    suspend fun getTopRatedMovies(): Flow<List<Movie>>
    suspend fun getTopRatedTVShwos(): Flow<List<Movie>>
    suspend fun getTrendingTVShows(): Flow<List<Movie>>
    suspend fun searchMovie(query: String): Flow<List<Movie>>
}