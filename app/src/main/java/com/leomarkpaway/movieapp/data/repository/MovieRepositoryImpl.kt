package com.leomarkpaway.movieapp.data.repository

import com.leomarkpaway.movieapp.data.source.local.database.AppDatabase
import com.leomarkpaway.movieapp.data.source.local.entity.Movie
import com.leomarkpaway.movieapp.data.source.preference.PreferenceManager
import com.leomarkpaway.movieapp.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val preference: PreferenceManager
) : MovieRepository {
    override suspend fun addMovie(movie: List<Movie>) {
        appDatabase.movieDao().insertAll(movie)
    }

    override suspend fun getAllMovies(): List<Movie> {
        return appDatabase.movieDao().getAllMovie()
    }

    override suspend fun sortByDate(): List<Movie> {
        return appDatabase.movieDao().sortByDate()
    }

    override suspend fun setDataBaseState(isDataBaseEmpty: Boolean) {
        preference.saveBoolean(preferenceKey = "preference_key", value = isDataBaseEmpty)
    }

    override suspend fun checkDataBaseState(): Boolean? {
        return preference.getBoolean(preferenceKey = "preference_key")
    }
}