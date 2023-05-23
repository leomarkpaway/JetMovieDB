package com.leomarkpaway.movieapp.presentation.ui.fragments

import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.leomarkpaway.movieapp.common.base.BaseFragment
import com.leomarkpaway.movieapp.common.util.collectLatestData
import com.leomarkpaway.movieapp.databinding.FragmentMovieDetailBinding
import com.leomarkpaway.movieapp.presentation.viewmodel.SharedViewModel

class MovieDetailFragment : BaseFragment<FragmentMovieDetailBinding, SharedViewModel>() {

    override fun initViews() {
        super.initViews()
        onBackPress()
    }

    override fun subscribe() {
        super.subscribe()
        viewModel.isDataBaseEmpty.collectLatestData(lifecycleScope) { state ->
            Log.d("qwe123", "State2331 $state")
        }
    }

    private fun onBackPress() {
        binding.imgBack.setOnClickListener { findNavController().popBackStack() }
    }

}