package com.leomarkpaway.movieapp.data.source.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.leomarkpaway.movieapp.common.base.BaseDao
import com.leomarkpaway.movieapp.data.source.local.entity.Movie

@Dao
interface MovieDao : BaseDao<Movie> {

    @Query("SELECT * FROM movie ORDER BY title ASC")
    suspend fun getAllMovie(): List<Movie>

    @Query("SELECT * FROM movie ORDER BY released_date_millis ASC")
    suspend fun sortByDate(): List<Movie>

    @Query("UPDATE movie SET isOnWatchlist = :isOnWatchlist WHERE id = :id")
    suspend fun addToWatchList(id: Long, isOnWatchlist: Boolean)

}