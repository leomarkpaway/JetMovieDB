package com.leomarkpaway.movieapp.data.source.remote.dto

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class MoviesDTO(
    var items: List<MovieItemDTO> = listOf()
) : Parcelable