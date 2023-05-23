package com.leomarkpaway.movieapp.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    @PrimaryKey(autoGenerate = true)
    val id :Long? = null,
    val title: String,
    val description: String,
    val rating: String,
    val duration: String,
    val genre: String,
    val released_date_millis: Long,
    val trailer_link: String,
    val isOnWatchlist: Boolean? = false,
    val image: String,
)
