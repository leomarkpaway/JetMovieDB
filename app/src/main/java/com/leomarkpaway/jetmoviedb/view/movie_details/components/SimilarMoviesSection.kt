package com.leomarkpaway.jetmoviedb.view.movie_details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.leomarkpaway.jetmoviedb.data.source.remote.entity.Movie
import com.leomarkpaway.jetmoviedb.view.home.viewmodel.HomeViewModel

@Composable
fun SimilarMoviesSection(currentMovie: Movie?, viewModel: HomeViewModel) {
    viewModel.getSimilarMovies(currentMovie?.id.toString())
    val similarMovies by viewModel.similarMoviesLiveData.observeAsState()
    similarMovies?.let { movies ->
        Text(text = "Similar Movies", style = typography.h5, modifier = Modifier.padding(8.dp))
        LazyRow {
            items(
                items = movies,
                itemContent = { movie: Movie ->
                    Image(
                        painter = rememberImagePainter(
                            data = "https://image.tmdb.org/t/p/w500/${movie.poster_path}"
                        ),
                        contentDescription = null,
                        modifier = Modifier
                            .width(200.dp)
                            .height(300.dp)
                            .padding(12.dp)
                            .clip(RoundedCornerShape(12.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
            )
        }
    }
}