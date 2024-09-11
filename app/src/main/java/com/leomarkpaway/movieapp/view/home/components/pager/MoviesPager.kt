package com.leomarkpaway.movieapp.view.home.components.pager

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.leomarkpaway.movieapp.R
import com.leomarkpaway.movieapp.intents.HomeInteractionEvents
import com.leomarkpaway.movieapp.view.home.viewmodel.HomeViewModel
import kotlin.math.abs

@Composable
fun MoviesPager(
    imageId: MutableState<Int>,
    moviesHomeInteractionEvents: (HomeInteractionEvents) -> Unit
) {
    val viewModel: HomeViewModel = viewModel()

    val movies by viewModel.nowShowingLiveData.observeAsState(emptyList())
    val genres by viewModel.genresLiveData.observeAsState(emptyList())
    val error by viewModel.errorLiveData.observeAsState()

    if (movies.isNotEmpty()) {
        val pagerState = remember { PagerState(maxPage = movies.size - 1) }

        Pager(state = pagerState, modifier = Modifier.height(645.dp)) {
            val movie = movies[commingPage]
            imageId.value = imageIds[pagerState.currentPage]
            val isSelected = pagerState.currentPage == commingPage

            val filteredOffset = if (abs(pagerState.currentPage - commingPage) < 2) {
                currentPageOffset
            } else 0f

            MoviePagerItem(
                movie,
                genres,
                isSelected,
                filteredOffset,
                { moviesHomeInteractionEvents(HomeInteractionEvents.AddToMyWatchlist(movie)) }
            ) {
                moviesHomeInteractionEvents(
                    HomeInteractionEvents.OpenMovieDetail(movie, imageId.value)
                )
            }
        }
    } else {
        if (error.isNullOrEmpty()) {
            CircularProgressIndicator(
                modifier = Modifier.padding(24.dp)
            )
        } else {
            Text(
                text = error ?: "Unknown error",
                modifier = Modifier,
                color = MaterialTheme.colors.error
            )
        }
    }
}

val imageIds =
    listOf(
        R.drawable.camelia,
        R.drawable.camelia,
        R.drawable.khalid,
        R.drawable.lana,
        R.drawable.edsheeran,
        R.drawable.dualipa,
        R.drawable.sam,
        R.drawable.marsh,
        R.drawable.wolves,
        R.drawable.camelia,
        R.drawable.khalid,
        R.drawable.lana,
        R.drawable.edsheeran,
        R.drawable.dualipa,
        R.drawable.sam,
        R.drawable.marsh,
        R.drawable.wolves,
        R.drawable.camelia,
        R.drawable.khalid,
        R.drawable.lana,
        R.drawable.edsheeran,
        R.drawable.dualipa,
        R.drawable.sam,
        R.drawable.marsh,
        R.drawable.wolves,
    )
