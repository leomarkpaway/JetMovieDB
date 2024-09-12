package com.leomarkpaway.movieapp.view.search

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.leomarkpaway.movieapp.intents.HomeInteractionEvents
import com.leomarkpaway.movieapp.ui.theme.modifiers.horizontalGradientBackground
import com.leomarkpaway.movieapp.ui.theme.moviesSurfaceGradient
import com.leomarkpaway.movieapp.view.search.viewmodel.SearchViewModel

@Composable
fun SearchScreen(moviesHomeInteractionEvents: (HomeInteractionEvents) -> Unit) {
    val viewModel: SearchViewModel = viewModel()
    val surfaceGradient = moviesSurfaceGradient(isSystemInDarkTheme())
    val searchResults by viewModel.searchResults.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Surface(modifier = Modifier.horizontalGradientBackground(surfaceGradient)) {
        var query by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.padding(10.dp))
            TextField(
                value = query,
                onValueChange = { newQuery ->
                    query = newQuery
                    Log.d("qwe", "newQuery $newQuery")
                    viewModel.search(newQuery)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, shape = RoundedCornerShape(24.dp)),
                placeholder = { Text(color = Color.LightGray, text = "Search") },
                shape = RoundedCornerShape(24.dp),
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Black,
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            if (isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(32.dp)
                    )
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.padding(16.dp)
                ) {
                    items(searchResults.size) { index ->
                        val movie = searchResults[index]
                        Image(
                            painter = rememberImagePainter(
                                data = "https://image.tmdb.org/t/p/w500/${movie.poster_path}"
                            ),
                            contentDescription = null,
                            modifier = Modifier
                                .width(200.dp)
                                .height(300.dp)
                                .padding(12.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .clickable(onClick = {
                                    moviesHomeInteractionEvents(
                                        HomeInteractionEvents.OpenMovieDetail(movie)
                                    )
                                }),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        }
    }
}