package com.leomarkpaway.movieapp.data.source.remote.dto

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class MovieItemDTO(
    @Json(name = "id") val id: Int? = null,
    @Json(name = "title") val title: String? = null,
    @Json(name = "description") val description: String? = null,
    @Json(name = "rating") val rating: String? = null,
    @Json(name = "duration") val duration: String? = null,
    @Json(name = "genre") val genre: String? = null,
    @Json(name = "releaseDate") val releaseDate: String? = null,
    @Json(name = "trailerUrl") val trailerUrl: String,
    @Json(name = "imageName") val image: String,
    @Json(name = "isOnWatchlist") var isOnWatchlist: Boolean
) : Parcelable