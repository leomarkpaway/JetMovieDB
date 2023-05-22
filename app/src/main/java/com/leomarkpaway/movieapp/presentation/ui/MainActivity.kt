package com.leomarkpaway.movieapp.presentation.ui

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
        viewModel.addMovie()
        viewModel.getAllMovies()
    }

    override fun subscribe() {
        super.subscribe()
        viewModel.allMovie.collectLatestData(lifecycleScope) { movieList ->
            if (!movieList.isNullOrEmpty()) setupMovieAdapter(movieList)
        }
    }

    private fun setupMovieAdapter(movieList : List<Movie>) = with(binding) {
        rvMovies.adapter = MovieAdapter(movieList) { id -> onClick(id) }
        rvMovies.layoutManager = LinearLayoutManager(this@MainActivity)
    }

    private fun onClick(id: Long?) {
        //TODO open movie detail screen
    }

}