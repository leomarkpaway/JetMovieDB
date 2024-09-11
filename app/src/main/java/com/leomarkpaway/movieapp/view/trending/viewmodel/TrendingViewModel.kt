package com.leomarkpaway.movieapp.view.trending.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leomarkpaway.movieapp.data.repository.MovieRepository
import com.leomarkpaway.movieapp.data.source.remote.entity.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrendingViewModel @Inject constructor(private val movieRepository: MovieRepository) : ViewModel() {
    val trendingMoviesLiveData = MutableLiveData<List<Movie>>()
    val popularMoviesLiveData = MutableLiveData<List<Movie>>()
    val topRatedMovies = MutableLiveData<List<Movie>>()
    val topRatedTVShows = MutableLiveData<List<Movie>>()
    val trendingTVShowsLiveData = MutableLiveData<List<Movie>>()

    init {
        viewModelScope.launch {
            movieRepository.getTrendingMovies().collect {
                trendingMoviesLiveData.value = it
            }
            movieRepository.getPopularMovies().collect {
                popularMoviesLiveData.value = it
            }
            movieRepository.getTopRatedMovies().collect {
                topRatedMovies.value = it
            }
            // TODO create new model for TV showsq
        }
    }
}