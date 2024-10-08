package com.leomarkpaway.jetmoviedb.model.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.leomarkpaway.jetmoviedb.model.source.local.database.daos.GenreDao
import com.leomarkpaway.jetmoviedb.model.source.local.database.daos.MoviesDao
import com.leomarkpaway.jetmoviedb.model.source.remote.entity.Genre
import com.leomarkpaway.jetmoviedb.model.source.remote.entity.Movie

const val VERSION_NUMBER = 1

@Database(entities = [Movie::class, Genre::class], version = VERSION_NUMBER, exportSchema = false)
@TypeConverters(ListTypeConverter::class)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
    abstract fun genreDao(): GenreDao
}