package com.leomarkpaway.movieapp.presentation.ui

import androidx.activity.viewModels
import com.leomarkpaway.movieapp.R
import com.leomarkpaway.movieapp.common.base.BaseActivity
import com.leomarkpaway.movieapp.databinding.ActivityMainBinding
import com.leomarkpaway.movieapp.presentation.viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<SharedViewModel, ActivityMainBinding>() {
    override val layoutId: Int = R.layout.activity_main
    override val viewModel: SharedViewModel by viewModels()
}