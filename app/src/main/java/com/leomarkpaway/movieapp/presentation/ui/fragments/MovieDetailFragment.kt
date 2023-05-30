package com.leomarkpaway.movieapp.presentation.ui.fragments

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.leomarkpaway.movieapp.R
import com.leomarkpaway.movieapp.common.base.BaseFragment
import com.leomarkpaway.movieapp.common.util.DateUtil.getConvertedDate
import com.leomarkpaway.movieapp.common.util.collectLatestData
import com.leomarkpaway.movieapp.common.util.convertByteArrayToBitmap
import com.leomarkpaway.movieapp.common.util.createBrowserIntent
import com.leomarkpaway.movieapp.common.util.openAsset
import com.leomarkpaway.movieapp.data.source.remote.dto.MovieItemDTO
import com.leomarkpaway.movieapp.databinding.FragmentMovieDetailBinding
import com.leomarkpaway.movieapp.presentation.viewmodel.SharedViewModel

class MovieDetailFragment : BaseFragment<FragmentMovieDetailBinding, SharedViewModel>() {

    private var isOnWatchlist: Boolean? = null
    private var movieId: Int? = null
    override fun initViews() {
        super.initViews()
        onClickAddToWatchList()
        onBackPress()
    }

    override fun subscribe() {
        super.subscribe()
        with(viewModel) {
            currentMovieSelected.collectLatestData(lifecycleScope) { movie ->
                if (movie != null) {
                    this@MovieDetailFragment.movieId = movie.id
                    setupMovieDetail(movie)
                }
            }
            isOnWatchlist.collectLatestData(lifecycleScope) { isOnWatchlist ->
                this@MovieDetailFragment.isOnWatchlist = isOnWatchlist
                binding.btnAddToWatchList.text = if (isOnWatchlist == true) {
                    requireContext().getString(R.string.remove_from_watchlist)
                } else {
                    requireContext().getString(R.string.add_to_watchlist)
                }
            }
        }
    }

    private fun setupMovieDetail(movie: MovieItemDTO) = with(binding) {
        imgMovie.setImageBitmap(byteArrayToBitmap(movie.image))
        tvMovieTitle.text = movie.title
        tvRating.text = requireContext().getString(R.string.rating, movie.rating)
        tvShortDescription.text = movie.description
        tvMovieGenre.text = movie.genre
        tvMovieReleaseDate.text = movie.releaseDate?.getConvertedDate()
        btnWatchTrailer.setOnClickListener { openUrlToBrowser(movie.trailerUrl) }
        viewModel.updateIsOnWatchlist(movie.isOnWatchlist)
    }

    private fun onClickAddToWatchList() {
        binding.btnAddToWatchList.setOnClickListener {
            movieId?.let { id -> viewModel.addToWatchList(id) }
            if (isOnWatchlist == true) {
                viewModel.updateIsOnWatchlist(false)
            } else {
                viewModel.updateIsOnWatchlist(true)
            }
        }
    }

    private fun byteArrayToBitmap(fileName: String) =
        requireContext().openAsset(fileName).readBytes().convertByteArrayToBitmap()

    private fun openUrlToBrowser(url: String) = startActivity(url.createBrowserIntent())

    private fun onBackPress() {
        binding.imgBack.setOnClickListener { findNavController().popBackStack() }
    }
}
