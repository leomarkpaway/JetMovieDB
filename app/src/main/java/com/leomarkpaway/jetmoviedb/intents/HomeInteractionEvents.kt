package com.leomarkpaway.jetmoviedb.intents

import com.leomarkpaway.jetmoviedb.data.source.remote.entity.Movie

sealed class HomeInteractionEvents {
    data class OpenMovieDetail(val movie: Movie, val imageId: Int = 0) : HomeInteractionEvents()
    data class AddToMyWatchlist(val movie: Movie) : HomeInteractionEvents()
    data class RemoveFromMyWatchlist(val movie: Movie) : HomeInteractionEvents()
}