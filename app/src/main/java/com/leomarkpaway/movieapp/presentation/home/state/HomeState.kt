package com.leomarkpaway.movieapp.presentation.home.state

import com.leomarkpaway.movieapp.data.source.remote.entity.Movie

data class HomeState(
    val nowShowingMovies: List<Movie> = emptyList()
)