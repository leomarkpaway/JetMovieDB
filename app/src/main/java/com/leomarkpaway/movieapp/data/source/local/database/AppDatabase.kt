package com.leomarkpaway.movieapp.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.leomarkpaway.movieapp.data.source.local.dao.MovieDao
import com.leomarkpaway.movieapp.data.source.local.entity.Movie

const val VERSION_NUMBER = 3

@Database(
    entities = [Movie::class],
    version = VERSION_NUMBER
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun movieDao() : MovieDao

}