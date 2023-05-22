package com.leomarkpaway.movieapp.presentation.ui

import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.leomarkpaway.movieapp.R
import com.leomarkpaway.movieapp.common.base.BaseActivity
import com.leomarkpaway.movieapp.common.util.collectLatestData
import com.leomarkpaway.movieapp.data.source.local.entity.Movie
import com.leomarkpaway.movieapp.databinding.ActivityMainBinding
import com.leomarkpaway.movieapp.presentation.ui.adapter.MovieAdapter
import com.leomarkpaway.movieapp.presentation.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainActivityViewModel, ActivityMainBinding>() {
    override val layoutId: Int = R.layout.activity_main
    override val viewModel: MainActivityViewModel by viewModels()

    override fun initViews() {
        super.initViews()
        viewModel.getAllMovies()
    }

    override fun subscribe() {
        super.subscribe()
        viewModel.allMovie.collectLatestData(lifecycleScope) { movieList ->
            if (movieList.isNullOrEmpty()) for (movie in movies) { viewModel.addMovie(movie) }
            else setupMovieAdapter(movieList)
        }
    }

    private fun setupMovieAdapter(movieList : List<Movie>) = with(binding) {
        rvMovies.adapter = MovieAdapter(movieList) { id -> onClick(id) }
        rvMovies.layoutManager = LinearLayoutManager(this@MainActivity)
    }

    private fun onClick(id: Long?) {
        //TODO open movie detail screen
    }

    companion object {
        val movies = listOf(
            Movie(
                title = "Tenet",
                description = "Armed with only one word, Tenet, and fighting for the survival of the entire world, a Protagonist journeys through a twilight world of international espionage on a mission that will unfold in something beyond real time.",
                rating = "7.8",
                duration = "2h 30 min",
                genre = "Action, Sci-Fi",
                released_date = "3 September 2020",
                trailer_link = "https://www.youtube.com/watch?v=LdOM0x0XDMo"
            ),
            Movie(
                title = "Spider-Man: Into the Spider-Verse",
                description = "Teen Miles Morales becomes the Spider-Man of his universe, and must join with five spider-powered individuals from other dimensions to stop a threat for all realities.",
                rating = "8.4",
                duration = "1h 57min",
                genre = "Action, Animation, Adventure",
                released_date = "14 December 2018 Trailer",
                trailer_link = "https://www.youtube.com/watch?v=tg52up16eq0 "
            ),
            Movie(
                title = "Knives Out",
                description = "A detective investigates the death of a patriarch of an eccentric, combative family.",
                rating = "7.9",
                duration = "2h 10min",
                genre = "Comedy, Crime, Drama",
                released_date = "27 November 2019",
                trailer_link = "https://www.youtube.com/watch?v=qGqiHJTsRkQ"
            ),
            Movie(
                title = "Guardians of the Galaxy",
                description = "A group of intergalactic criminals must pull together to stop a fanatical warrior with plans to purge the universe.",
                rating = "8.0",
                duration = "2h 1min",
                genre = "Action, Adventure, Comedy",
                released_date = "1 August 2014",
                trailer_link = "https://www.youtube.com/watch?v=d96cjJhvlMA"
            ),
            Movie(
                title = "Avengers: Age of Ultron",
                description = "When Tony Stark and Bruce Banner try to jump-start a dormant peacekeeping program called Ultron, things go horribly wrong and it's up to Earth's mightiest heroes to stop the villainous Ultron from enacting his terrible plan. ",
                rating = "7.3",
                duration = "2h 21min",
                genre = "Action, Adventure, Sci-Fi",
                released_date = "1 May 2015",
                trailer_link = "https://www.youtube.com/watch?v=tmeOjFno6Do"
            )
        )
    }

}