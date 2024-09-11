package com.leomarkpaway.movieapp.presentation.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import androidx.compose.material.icons.outlined.Subscriptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.leomarkpaway.movieapp.presentation.home.MovieHomeScreen
import com.leomarkpaway.movieapp.presentation.home.intent.MoviesHomeInteractionEvents
import com.leomarkpaway.movieapp.presentation.home.state.HomeState
import com.leomarkpaway.movieapp.presentation.home.state.UIComponent
import com.leomarkpaway.movieapp.presentation.viewmodel.MainActivityViewModel
import com.leomarkpaway.movieapp.ui.theme.MovieAppTheme
import com.leomarkpaway.movieapp.ui.theme.graySurface
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        setContent {
            MovieAppTheme {
                val navType = rememberSaveable { mutableStateOf(MovieNavType.SHOWING) }
                val viewModel: MainActivityViewModel = viewModel()
                viewModel.observe(state = ::render, sideEffect = ::handleSideEffect, lifecycleOwner = this)
                Scaffold(
                    bottomBar = { MoviesBottomBar(navType) }
                ) {
                    Crossfade(
                        targetState = navType,
                        modifier = Modifier.padding(it),
                    ) { navTypeState ->
                        when (navTypeState.value) {
                            MovieNavType.SHOWING -> MovieHomeScreen(
                                moviesHomeInteractionEvents = { event ->
                                    handleInteractionEvents(event, viewModel)
                                }
                            )
//                            MovieNavType.TRENDING -> MovieTrendingScreen(
//                                moviesHomeInteractionEvents = { event ->
//                                    handleInteractionEvents(event, viewModel)
//                                }
//                            )
//                            MovieNavType.WATCHLIST -> WatchlistScreen(
//                                moviesHomeInteractionEvents = { event ->
//                                    handleInteractionEvents(event, viewModel)
//                                }
//                            )
                            else -> {}
                        }
                    }
                }
            }
        }
    }

    private fun render(state: HomeState) {
        Log.d("render", "state $state")
    }

    private fun handleSideEffect(sideEffect: UIComponent) {
        Log.d("handleSideEffect", "sideEffect $sideEffect")
    }

    private fun handleInteractionEvents(
        interactionEvents: MoviesHomeInteractionEvents,
        viewModel: MainActivityViewModel
    ) {
        when (interactionEvents) {
            is MoviesHomeInteractionEvents.OpenMovieDetail -> {
//                startActivity(
//                    MovieDetailActivity.newIntent(
//                        this, interactionEvents.movie, interactionEvents.imageId
//                    )
//                )
                overridePendingTransition(0, 0)
            }
            is MoviesHomeInteractionEvents.AddToMyWatchlist -> {
                viewModel.addToMyWatchlist(interactionEvents.movie)
            }
            is MoviesHomeInteractionEvents.RemoveFromMyWatchlist -> {
                viewModel.removeFromMyWatchlist(interactionEvents.movie)
            }
        }
    }

    companion object {
        private const val DARK_THEME = "darkTheme"
        fun newIntent(context: Context, isDarkTheme: Boolean) =
            Intent(context, MainActivity::class.java).apply {
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
    }
}

enum class MovieNavType {
    SHOWING, TRENDING, WATCHLIST
}
