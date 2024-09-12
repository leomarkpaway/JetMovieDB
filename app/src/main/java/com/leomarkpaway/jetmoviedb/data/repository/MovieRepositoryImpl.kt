package com.leomarkpaway.jetmoviedb.data.repository

import androidx.lifecycle.LiveData
import com.leomarkpaway.jetmoviedb.BuildConfig
import com.leomarkpaway.jetmoviedb.data.source.local.database.MoviesDatabase
import com.leomarkpaway.jetmoviedb.data.source.local.database.daos.GenreDao
import com.leomarkpaway.jetmoviedb.data.source.local.database.daos.MoviesDao
import com.leomarkpaway.jetmoviedb.data.source.remote.entity.Genre
import com.leomarkpaway.jetmoviedb.data.source.remote.entity.Movie
import com.leomarkpaway.jetmoviedb.data.source.remote.service.MovieApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MovieRepositoryImpl(
    private val movieApi: MovieApi,
    private val moviesDatabase: MoviesDatabase,
) : MovieRepository {

    private val moviesDao: MoviesDao = moviesDatabase.moviesDao()
    private val genreDao: GenreDao = moviesDatabase.genreDao()

    override suspend fun getNowShowing(): Flow<List<Movie>> = flow {
        val response = movieApi.getMovies(1)
        if (response.isSuccessful) {
            emit(response.body()?.movies ?: emptyList<Movie>())
        } else {
            emit(emptyList<Movie>())
        }

    }.catch { e ->
        emit(emptyList<Movie>())
    }.flowOn(Dispatchers.IO)

    override suspend fun getSimilarMovies(movieId: String): Flow<List<Movie>> = flow {
        val response = movieApi.getSimilarMovies(movieId)
        if (response.isSuccessful) {
            emit(response.body()?.movies ?: emptyList<Movie>())
        } else {
            emit(emptyList<Movie>())
        }

    }.catch {
        emit(emptyList<Movie>())
    }.flowOn(Dispatchers.IO)

    override suspend fun getMyWatchlist(): LiveData<List<Movie>> {
        return moviesDao.getMyWatchlist()
    }

    override suspend fun addToMyWatchlist(movie: Movie) {
        moviesDao.addToWatchList(movie)
    }

    override suspend fun removeFromMyWatchlist(movie: Movie) {
        moviesDao.removeFromMYWatchlist(movie)
    }

    override suspend fun getGenres(): LiveData<List<Genre>> = genreDao.getAllGenres()

    override suspend fun fetchAndSaveGenresToDatabase(): Flow<List<Genre>> = flow {
        val response = movieApi.getGenres()
        if (response.isSuccessful) {
            response.body()?.let {
                if (it.genres.isNotEmpty()) {
                    genreDao.insertAllGenres(it.genres)
                }
            }
        } else {
            emit(emptyList<Genre>())
        }
    }.catch {
        emit(emptyList<Genre>())
    }.flowOn(Dispatchers.IO)

    override suspend fun getTrendingMovies(): Flow<List<Movie>> = flow {
        val response = movieApi.getTrendingMovies()
        if (response.isSuccessful) {
            emit(response.body()?.movies ?: emptyList<Movie>())
        } else {
            emit(emptyList<Movie>())
        }

    }.catch {
        emit(emptyList<Movie>())
    }.flowOn(Dispatchers.Default)

    override suspend fun getTrendingTVShows(): Flow<List<Movie>> = flow {
        val response = movieApi.getTrendingTVShows()
        if (response.isSuccessful) {
            emit(response.body()?.movies ?: emptyList<Movie>())
        } else {
            emit(emptyList<Movie>())
        }

    }.catch {
        emit(emptyList<Movie>())
    }.flowOn(Dispatchers.Default)

    override suspend fun getPopularMovies(): Flow<List<Movie>> = flow {
        val response = movieApi.getPopularMovies()
        if (response.isSuccessful) {
            emit(response.body()?.movies ?: emptyList<Movie>())
        } else {
            emit(emptyList<Movie>())
        }

    }.catch {
        emit(emptyList<Movie>())
    }.flowOn(Dispatchers.Default)

    override suspend fun getTopRatedMovies(): Flow<List<Movie>> = flow {
        val response = movieApi.getTopRatedMovies()
        if (response.isSuccessful) {
            emit(response.body()?.movies ?: emptyList<Movie>())
        } else {
            emit(emptyList<Movie>())
        }

    }.catch {
        emit(emptyList<Movie>())
    }.flowOn(Dispatchers.Default)

    override suspend fun getTopRatedTVShwos(): Flow<List<Movie>> = flow {
        val response = movieApi.getTopRatedTvShows()
        if (response.isSuccessful) {
            emit(response.body()?.movies ?: emptyList<Movie>())
        } else {
            emit(emptyList<Movie>())
        }

    }.catch {
        emit(emptyList<Movie>())
    }.flowOn(Dispatchers.Default)

    override suspend fun searchMovie(query: String): Flow<List<Movie>> = flow {
        val response = movieApi.searchMovies(query = query, apiKey = BuildConfig.MOVIE_API_KEY)
        if (response.isSuccessful) {
            emit(response.body()?.movies ?: emptyList())
        } else {
            emit(emptyList())
        }
    }.catch {
        emit(emptyList())
    }.flowOn(Dispatchers.Default)

}