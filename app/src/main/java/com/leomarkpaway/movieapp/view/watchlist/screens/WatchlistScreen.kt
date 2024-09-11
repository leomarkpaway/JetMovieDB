package com.leomarkpaway.movieapp.view.watchlist.screens

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.leomarkpaway.movieapp.data.source.remote.entity.Movie
import com.leomarkpaway.movieapp.intents.HomeInteractionEvents
import com.leomarkpaway.movieapp.ui.theme.modifiers.horizontalGradientBackground
import com.leomarkpaway.movieapp.ui.theme.moviesSurfaceGradient
import com.leomarkpaway.movieapp.view.home.viewmodel.HomeViewModel
import com.leomarkpaway.movieapp.view.watchlist.components.WatchlistItem

@Composable
fun WatchlistScreen(moviesHomeInteractionEvents: (HomeInteractionEvents) -> Unit) {
    val surfaceGradient = moviesSurfaceGradient(isSystemInDarkTheme())
    val viewModel: HomeViewModel = viewModel()
    val myWatchlist by viewModel.myWatchlist.observeAsState(emptyList())
    if (myWatchlist.isEmpty()) {
        Column {
            Spacer(modifier = Modifier.padding(100.dp))
            Text(
                text = "Watchlist is empty",
                style = MaterialTheme.typography.h6,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Text(
                text = "Please add some movies to your watchlist",
                style = MaterialTheme.typography.caption,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )
        }
        return
    }

    Surface(modifier = Modifier.horizontalGradientBackground(surfaceGradient)) {
        LazyColumn {
            itemsIndexed(
                items = myWatchlist,
                itemContent = { _: Int, movie: Movie ->
                    WatchlistItem(
                        movie,
                        {
                            moviesHomeInteractionEvents(
                                HomeInteractionEvents.OpenMovieDetail(movie)
                            )
                        },
                        {
                            moviesHomeInteractionEvents(
                               HomeInteractionEvents.RemoveFromMyWatchlist(movie)
                            )
                        }
                    )
                }
            )
        }
    }
}



