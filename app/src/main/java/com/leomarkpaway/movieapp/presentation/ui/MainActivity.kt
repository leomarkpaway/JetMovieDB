package com.leomarkpaway.movieapp.presentation.ui

import androidx.activity.viewModels
import com.leomarkpaway.movieapp.R
import com.leomarkpaway.movieapp.common.base.BaseActivity
import com.leomarkpaway.movieapp.databinding.ActivityMainBinding
import com.leomarkpaway.movieapp.presentation.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainActivityViewModel, ActivityMainBinding>() {
    override val layoutId: Int = R.layout.activity_main
    override val viewModel: MainActivityViewModel by viewModels()

    override fun initViews() {
        super.initViews()
        //TODO initial view here
    }

    override fun subscribe() {
        super.subscribe()
        //TODO observe data here
    }

}