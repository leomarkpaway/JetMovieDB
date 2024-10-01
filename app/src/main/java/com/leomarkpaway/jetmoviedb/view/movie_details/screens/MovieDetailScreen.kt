package com.leomarkpaway.jetmoviedb.view.movie_details.screens

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LibraryAdd
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.leomarkpaway.jetmoviedb.model.source.remote.entity.Movie
import com.leomarkpaway.jetmoviedb.ui.theme.extension.generateDominantColorState
import com.leomarkpaway.jetmoviedb.ui.theme.graySurface
import com.leomarkpaway.jetmoviedb.ui.theme.modifiers.verticalGradientBackground
import com.leomarkpaway.jetmoviedb.view.home.viewmodel.HomeViewModel
import com.leomarkpaway.jetmoviedb.view.movie_details.components.GenreSection
import com.leomarkpaway.jetmoviedb.view.movie_details.components.SimilarMoviesSection

@ExperimentalCoilApi
@Composable
fun MovieDetailScreen(movie: Movie, imageId: Int) {
    val expand = remember { mutableStateOf(false) }
    val viewModel: HomeViewModel = viewModel()
    var dominantColors = listOf(graySurface, Color.Black)

    if (imageId != 0) {
        val context = LocalContext.current
        val currentBitmap = ImageBitmap.imageResource(context.resources, imageId)

        val swatch = currentBitmap.asAndroidBitmap().generateDominantColorState()
        dominantColors = listOf(Color(swatch.rgb), Color.Black)
    }

    LazyColumn(
        modifier = Modifier
            .verticalGradientBackground(dominantColors)
            .padding(
                animateDpAsState(
                    if (expand.value) 1.dp else 120.dp,
                    tween(350)
                ).value
            )
    ) {
        item {
            val painter = rememberImagePainter(
                data = "https://image.tmdb.org/t/p/w500/${movie.poster_path}"
            )
            Image(
                painter = painter,
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .height(
                        600.dp
                    )
                    .fillMaxWidth(),
            )
            when (painter.state) {
                is ImagePainter.State.Success -> expand.value = true
                else -> expand.value = false
            }
        }
        item {
            Column(modifier = Modifier.background(MaterialTheme.colors.onSurface)) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = movie.title,
                        modifier = Modifier.padding(8.dp),
                        style = typography.h6
                    )
                    IconButton(onClick = {viewModel.addToMyWatchlist(movie)}) {
                        Icon(
                            imageVector = Icons.Default.LibraryAdd,
                            contentDescription = null,
                            tint = MaterialTheme.colors.primary
                        )
                    }
                }
                GenreSection(viewModel, movie.genre_ids)
                Text(
                    text = "Release: ${movie.release_date}",
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    style = typography.h6.copy(fontSize = 12.sp)
                )
                Text(
                    text = "PG13  •  ${movie.vote_average}/10",
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    style = typography.h6.copy(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                )
                Text(
                    text = movie.overview,
                    modifier = Modifier
                        .padding(8.dp),
                    style = typography.subtitle2
                )
                Spacer(modifier = Modifier.height(20.dp))
                SimilarMoviesSection(movie, viewModel)
                Spacer(modifier = Modifier.height(50.dp))
            }
        }

    }
}
