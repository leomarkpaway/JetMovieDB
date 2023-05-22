package com.leomarkpaway.movieapp.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase

const val VERSION_NUMBER = 1

@Database(
    entities = [/*TODO add your entity class here*/],
    version = VERSION_NUMBER
)
abstract class AppDatabase: RoomDatabase() {

    //TODO add abstract DAO here

}