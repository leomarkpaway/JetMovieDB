package com.leomarkpaway.jetmoviedb.view.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LibraryAdd
import androidx.compose.material.icons.outlined.MovieCreation
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Subscriptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.annotation.ExperimentalCoilApi
import com.leomarkpaway.jetmoviedb.intents.HomeInteractionEvents
import com.leomarkpaway.jetmoviedb.ui.theme.MovieAppTheme
import com.leomarkpaway.jetmoviedb.ui.theme.graySurface
import com.leomarkpaway.jetmoviedb.view.home.screens.HomeScreen
import com.leomarkpaway.jetmoviedb.view.home.viewmodel.HomeViewModel
import com.leomarkpaway.jetmoviedb.view.movie_details.MovieDetailActivity
import com.leomarkpaway.jetmoviedb.view.search.SearchScreen
import com.leomarkpaway.jetmoviedb.view.trending.screens.TrendingScreen
import com.leomarkpaway.jetmoviedb.view.watchlist.screens.WatchlistScreen
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalCoilApi
@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        setContent {
            MovieAppTheme {
                val navType = rememberSaveable { mutableStateOf(MovieNavType.SHOWING) }
                val viewModel: HomeViewModel = viewModel()
                Scaffold(
                    bottomBar = { MoviesBottomBar(navType) }
                ) {
                    Crossfade(
                        targetState = navType,
                        modifier = Modifier.padding(it),
                    ) { navTypeState ->
                        when (navTypeState.value) {
                            MovieNavType.SHOWING -> HomeScreen(
                                moviesHomeInteractionEvents = { event ->
                                    handleInteractionEvents(event, viewModel)
                                }
                            )
                            MovieNavType.TRENDING -> TrendingScreen(
                                moviesHomeInteractionEvents = { event ->
                                    handleInteractionEvents(event, viewModel)
                                }
                            )
                            MovieNavType.WATCHLIST -> WatchlistScreen(
                                moviesHomeInteractionEvents = { event ->
                                    handleInteractionEvents(event, viewModel)
                                }
                            )
                            MovieNavType.SEARCH -> SearchScreen(
                                moviesHomeInteractionEvents = { event ->
                                    handleInteractionEvents(event, viewModel)
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    private fun handleInteractionEvents(
        interactionEvents: HomeInteractionEvents,
        viewModel: HomeViewModel
    ) {
        when (interactionEvents) {
            is HomeInteractionEvents.OpenMovieDetail -> {
                startActivity(
                    MovieDetailActivity.newIntent(
                        this, interactionEvents.movie, interactionEvents.imageId
                    )
                )
                overridePendingTransition(0, 0)
            }
            is HomeInteractionEvents.AddToMyWatchlist -> {
                viewModel.addToMyWatchlist(interactionEvents.movie)
            }
            is HomeInteractionEvents.RemoveFromMyWatchlist -> {
                viewModel.removeFromMyWatchlist(interactionEvents.movie)
            }
        }
    }

    companion object {
        private const val DARK_THEME = "darkTheme"
        fun newIntent(context: Context, isDarkTheme: Boolean) =
            Intent(context, HomeActivity::class.java).apply {
                putExtra(DARK_THEME, isDarkTheme)
            }
    }
}

@Composable
fun MoviesBottomBar(navType: MutableState<MovieNavType>) {
    val bottomNavBackground =
        if (isSystemInDarkTheme()) graySurface else MaterialTheme.colors.background
    BottomNavigation(backgroundColor = bottomNavBackground) {
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Outlined.MovieCreation, contentDescription = null) },
            selected = navType.value == MovieNavType.SHOWING,
            onClick = { navType.value = MovieNavType.SHOWING },
            label = { Text(text = "Showing") },
        )
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Outlined.Subscriptions, contentDescription = null) },
            selected = navType.value == MovieNavType.TRENDING,
            onClick = { navType.value = MovieNavType.TRENDING },
            label = { Text(text = "Trending") }
        )
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Outlined.LibraryAdd, contentDescription = null) },
            selected = navType.value == MovieNavType.WATCHLIST,
            onClick = { navType.value = MovieNavType.WATCHLIST },
            label = { Text(text = "Watchlist") }
        )
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Outlined.Search, contentDescription = null) },
            selected = navType.value == MovieNavType.SEARCH,
            onClick = { navType.value = MovieNavType.SEARCH },
            label = { Text(text = "Search") }
        )
    }
}

enum class MovieNavType {
    SHOWING, TRENDING, WATCHLIST,SEARCH
}
