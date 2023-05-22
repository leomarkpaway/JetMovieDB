package com.leomarkpaway.movieapp.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.leomarkpaway.movieapp.data.source.local.entity.Movie

const val VERSION_NUMBER = 1

@Database(
    entities = [Movie::class],
    version = VERSION_NUMBER
)
abstract class AppDatabase: RoomDatabase() {

    //TODO add abstract DAO here

}