package com.leomarkpaway.movieapp.view.movie_details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import coil.annotation.ExperimentalCoilApi
import com.leomarkpaway.movieapp.data.source.remote.entity.Movie
import com.leomarkpaway.movieapp.ui.theme.MovieAppTheme
import com.leomarkpaway.movieapp.view.movie_details.screens.MovieDetailScreen
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalCoilApi
@AndroidEntryPoint
class MovieDetailActivity : ComponentActivity() {
    val movie by lazy {
        intent.getSerializableExtra(MOVIE) as Movie?
    }
    val imageId by lazy {
        intent.getIntExtra(IMAGE_ID, 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        setContent {
            MovieAppTheme {
                movie?.let {
                    MovieDetailScreen(it, imageId)
                }
            }
        }
    }

    companion object {
        const val MOVIE = "movie"
        const val IMAGE_ID = "imageId"
        fun newIntent(context: Context, movie: Movie) =
            Intent(context, MovieDetailActivity::class.java).apply {
                putExtra(MOVIE, movie)
            }

        fun newIntent(context: Context, movie: Movie, imageId: Int) =
            Intent(context, MovieDetailActivity::class.java).apply {
                putExtra(MOVIE, movie)
                putExtra(IMAGE_ID, imageId)
            }
    }
}
