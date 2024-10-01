package com.leomarkpaway.jetmoviedb.model.source.remote.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genres")
data class Genre(
    @PrimaryKey
    val id: Int,
    val name: String
)

data class GenreApiResponse(val genres: List<Genre>)