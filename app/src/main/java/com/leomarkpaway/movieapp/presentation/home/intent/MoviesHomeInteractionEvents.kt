package com.leomarkpaway.movieapp.presentation.home.intent

import com.leomarkpaway.movieapp.data.source.remote.entity.Movie

sealed class MoviesHomeInteractionEvents {
    data class OpenMovieDetail(val movie: Movie, val imageId: Int = 0) : MoviesHomeInteractionEvents()
    data class AddToMyWatchlist(val movie: Movie) : MoviesHomeInteractionEvents()
    data class RemoveFromMyWatchlist(val movie: Movie) : MoviesHomeInteractionEvents()
}