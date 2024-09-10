package com.leomarkpaway.movieapp.data.source.local.database.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.leomarkpaway.movieapp.data.source.remote.entity.Movie

@Dao
@TypeConverters()
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addToWatchList(movie: Movie)

    @Transaction
    @Query("select * from movies_table")
    fun getMyWatchlist(): LiveData<List<Movie>>

    @Query("DELETE FROM movies_table")
    fun deleteAll()

    @Query("select * from movies_table where id =:movieId")
    fun getMovieDetail(movieId: String): LiveData<Movie>

    @Delete
    fun removeFromMYWatchlist(movie: Movie)
}