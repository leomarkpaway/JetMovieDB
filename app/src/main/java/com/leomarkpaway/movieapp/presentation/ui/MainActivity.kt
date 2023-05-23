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

    private var isSortByDate = false

    override fun initViews() {
        super.initViews()
        viewModel.addMovie()
        onClickSort()
    }

    override fun subscribe() {
        super.subscribe()
        viewModel.allMovie.collectLatestData(lifecycleScope) { movieList ->
            if (!movieList.isNullOrEmpty()) setupMovieAdapter(movieList)
        }
        viewModel.isSortByDate.collectLatestData(lifecycleScope) { isSortByDate ->
            this.isSortByDate = isSortByDate
            if (isSortByDate) viewModel.sortByDate()
            else viewModel.getAllMovies()
        }
    }

    private fun onClickSort() = with(viewModel){
        binding.tvSort.setOnClickListener {
            if (!this@MainActivity.isSortByDate) updateIsSortByDate(true)
            else updateIsSortByDate(false)
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