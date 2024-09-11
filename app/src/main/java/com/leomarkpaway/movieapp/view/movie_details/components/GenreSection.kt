package com.leomarkpaway.movieapp.view.movie_details.components

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.leomarkpaway.movieapp.view.home.components.tags.InterestTag
import com.leomarkpaway.movieapp.view.movie_details.viewmodel.MovieDetailViewModel

@Composable
fun GenreSection(viewModel: MovieDetailViewModel, movieGenreIdArray: List<Int>?) {
    movieGenreIdArray?.let { movieGenreIds ->
        val genres by viewModel.genresLiveData.observeAsState(emptyList())
        val movieGenres = genres.filter { movieGenreIds.contains(it.id) }.take(3)
        Row {
            movieGenres.forEach {
                InterestTag(text = it.name)
            }
        }
    }
}
