package com.leomarkpaway.movieapp.data.source.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.leomarkpaway.movieapp.common.base.BaseDao
import com.leomarkpaway.movieapp.data.source.local.entity.Movie

@Dao
interface MovieDao : BaseDao<Movie> {

    @Query("SELECT * FROM movie ORDER BY id DESC")
    suspend fun getAllMovie(): List<Movie>

}