package com.leomarkpaway.movieapp.data.source.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.leomarkpaway.movieapp.data.source.local.database.daos.GenreDao
import com.leomarkpaway.movieapp.data.source.local.database.daos.MoviesDao
import com.leomarkpaway.movieapp.data.source.remote.entity.Genre
import com.leomarkpaway.movieapp.data.source.remote.entity.Movie

const val VERSION_NUMBER = 1

@Database(entities = [Movie::class, Genre::class], version = VERSION_NUMBER, exportSchema = false)
@TypeConverters(ListTypeConverter::class)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
    abstract fun genreDao(): GenreDao

    companion object {
        private var instance: MoviesDatabase? = null
        private val LOCK = Any()

        fun getInstance(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context.applicationContext).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                MoviesDatabase::class.java, "movie_database.db"
            ).build()
    }
}