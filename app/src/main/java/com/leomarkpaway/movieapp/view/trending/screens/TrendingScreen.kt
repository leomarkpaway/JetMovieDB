package com.leomarkpaway.movieapp.view.trending.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.leomarkpaway.movieapp.intents.HomeInteractionEvents
import com.leomarkpaway.movieapp.ui.theme.graySurface
import com.leomarkpaway.movieapp.ui.theme.modifiers.horizontalGradientBackground
import com.leomarkpaway.movieapp.view.trending.components.MovieCategoryItem
import com.leomarkpaway.movieapp.view.trending.viewmodel.TrendingViewModel

@Composable
fun TrendingScreen(moviesHomeInteractionEvents: (HomeInteractionEvents) -> Unit) {
    val surfaceGradient = listOf(graySurface, Color.Black)
    val viewModel: TrendingViewModel = viewModel()
    val showLoading = remember { mutableStateOf(true) }
    val listOfSections = listOf(
        "Trending this week",
        "Popular this week",
        "Top rated movies",
        "Trending TV shows",
        "Top rated TV shows",
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .horizontalGradientBackground(surfaceGradient)
            .verticalScroll(rememberScrollState())
    ) {
        // TODO Implement loading state
//        if (showLoading.value) {
//            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
//        }

        listOfSections.forEach {
            DynamicSection(it, viewModel, showLoading, moviesHomeInteractionEvents)
        }
    }
}

@Composable
fun DynamicSection(
    type: String,
    viewModel: TrendingViewModel,
    showLoading: MutableState<Boolean>,
    moviesHomeInteractionEvents: (HomeInteractionEvents) -> Unit
) {
    val movies by when (type) {
        "Trending this week" -> viewModel.trendingMoviesLiveData.observeAsState(emptyList())
        "Popular this week" -> viewModel.popularMoviesLiveData.observeAsState(emptyList())
        "Trending TV shows" -> viewModel.trendingTVShowsLiveData.observeAsState(emptyList())
        "Top rated movies" -> viewModel.topRatedMovies.observeAsState(emptyList())
        "Top rated TV shows" -> viewModel.topRatedTVShows.observeAsState(emptyList())
        else -> viewModel.trendingMoviesLiveData.observeAsState(emptyList())
    }
    if (movies.isNotEmpty()) {
        showLoading.value = false
        MovieCategoryItem(movies = movies, title = type) { movie ->
            moviesHomeInteractionEvents(
                HomeInteractionEvents.OpenMovieDetail(movie = movie)
            )
        }
    }
}