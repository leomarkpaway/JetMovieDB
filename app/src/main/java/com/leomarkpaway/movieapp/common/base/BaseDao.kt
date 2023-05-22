package com.leomarkpaway.movieapp.common.base

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import androidx.room.Delete

@Dao
interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg data: T?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<T>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: T): Long

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(vararg data: T)

    @Delete
    suspend fun delete(vararg data: T)

}