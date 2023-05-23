package com.leomarkpaway.movieapp.presentation.ui.fragments

import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.leomarkpaway.movieapp.R
import com.leomarkpaway.movieapp.common.base.BaseFragment
import com.leomarkpaway.movieapp.common.util.collectLatestData
import com.leomarkpaway.movieapp.common.util.convertByteArrayToBitmap
import com.leomarkpaway.movieapp.common.util.formatDateFromMillis
import com.leomarkpaway.movieapp.common.util.openAsset
import com.leomarkpaway.movieapp.data.source.local.entity.Movie
import com.leomarkpaway.movieapp.databinding.FragmentMovieDetailBinding
import com.leomarkpaway.movieapp.presentation.viewmodel.SharedViewModel

class MovieDetailFragment : BaseFragment<FragmentMovieDetailBinding, SharedViewModel>() {

    override fun initViews() {
        super.initViews()
        onBackPress()
    }

    override fun subscribe() {
        super.subscribe()
        viewModel.currentMovieSelected.collectLatestData(lifecycleScope) { movie ->
            if (movie != null) setupMovieDetail(movie)
        }
    }

    private fun setupMovieDetail(movie: Movie) = with(binding) {
        imgMovie.setImageBitmap(byteArrayToBitmap(movie.image))
        tvMovieTitle.text = movie.title
        tvRating.text = requireContext().getString(R.string.rating, movie.rating)
        tvShortDescription.text = movie.description
        tvMovieGenre.text = movie.genre
        tvMovieReleaseDate.text = formatDateFromMillis(movie.released_date_millis)
    }

    private fun byteArrayToBitmap(fileName: String) =
        requireContext().openAsset(fileName).readBytes().convertByteArrayToBitmap()

    private fun onBackPress() {
        binding.imgBack.setOnClickListener { findNavController().popBackStack() }
    }

}