package com.leomarkpaway.movieapp.presentation.ui.fragments

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.leomarkpaway.movieapp.R
import com.leomarkpaway.movieapp.common.base.BaseFragment
import com.leomarkpaway.movieapp.common.util.collectLatestData
import com.leomarkpaway.movieapp.common.util.formatDateFromMillis
import com.leomarkpaway.movieapp.common.util.openAsset
import com.leomarkpaway.movieapp.common.util.convertByteArrayToBitmap
import com.leomarkpaway.movieapp.common.util.createBrowserIntent
import com.leomarkpaway.movieapp.data.source.local.entity.Movie
import com.leomarkpaway.movieapp.databinding.FragmentMovieDetailBinding
import com.leomarkpaway.movieapp.presentation.viewmodel.SharedViewModel

class MovieDetailFragment : BaseFragment<FragmentMovieDetailBinding, SharedViewModel>() {

    private var isOnWatchlist: Boolean? = null
    private var movieId: Long? = null
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

    private fun setupMovieDetail(movie: Movie) = with(binding) {
        imgMovie.setImageBitmap(byteArrayToBitmap(movie.image))
        tvMovieTitle.text = movie.title
        tvRating.text = requireContext().getString(R.string.rating, movie.rating)
        tvShortDescription.text = movie.description
        tvMovieGenre.text = movie.genre
        tvMovieReleaseDate.text = formatDateFromMillis(movie.released_date_millis)
        btnWatchTrailer.setOnClickListener { openUrlToBrowser(movie.trailer_link) }
        if (movie.isOnWatchlist != null) viewModel.updateIsOnWatchlist(movie.isOnWatchlist)
    }

    private fun  onClickAddToWatchList() {
        binding.btnAddToWatchList.setOnClickListener {
            if (isOnWatchlist == true) {
                viewModel.updateIsOnWatchlist(false)
                movieId?.let { id -> viewModel.addToWatchList(id, false) }
            } else {
                viewModel.updateIsOnWatchlist(true)
                movieId?.let { id -> viewModel.addToWatchList(id, true) }
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