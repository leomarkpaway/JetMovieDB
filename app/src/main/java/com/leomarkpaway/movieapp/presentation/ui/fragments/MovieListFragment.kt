package com.leomarkpaway.movieapp.presentation.ui.fragments

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.leomarkpaway.movieapp.R
import com.leomarkpaway.movieapp.common.base.BaseFragment
import com.leomarkpaway.movieapp.common.util.collectLatestData
import com.leomarkpaway.movieapp.data.source.remote.dto.MovieItemDTO
import com.leomarkpaway.movieapp.databinding.FragmentMovieListBinding
import com.leomarkpaway.movieapp.presentation.ui.adapter.MovieAdapter
import com.leomarkpaway.movieapp.presentation.viewmodel.SharedViewModel

class MovieListFragment : BaseFragment<FragmentMovieListBinding, SharedViewModel>() {

    private var isSortByDate = false
    override fun initViews() {
        super.initViews()
        viewModel.getAllMovies()
        onClickSort()
    }

    override fun subscribe() {
        super.subscribe()
        with(viewModel) {
            allMovie.collectLatestData(lifecycleScope) { movieList ->
                if (!movieList.isNullOrEmpty()) {
                    setupMovieAdapter(movieList)
                }
            }
            isSortByDate.collectLatestData(lifecycleScope) { isSortByDate ->
                this@MovieListFragment.isSortByDate = isSortByDate
                if (isSortByDate) sortMoviesByDate() else sortMoviesByTitle()
            }
        }
    }

    private fun onClickSort() = with(viewModel) {
        binding.tvSort.setOnClickListener {
            if (!this@MovieListFragment.isSortByDate) updateIsSortByDate(true)
            else updateIsSortByDate(false)
        }
    }

    private fun setupMovieAdapter(movieList : List<MovieItemDTO>) = with(binding) {
        rvMovies.adapter = MovieAdapter(movieList) { movie -> onClickMovie(movie) }
        rvMovies.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun onClickMovie(movie: MovieItemDTO) {
        viewModel.setCurrentMovieSelected(movie)
        findNavController().navigate(R.id.action_movieDetail)
    }

}