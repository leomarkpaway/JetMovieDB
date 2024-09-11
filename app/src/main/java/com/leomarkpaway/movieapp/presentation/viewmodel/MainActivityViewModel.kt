package com.leomarkpaway.movieapp.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.leomarkpaway.movieapp.data.repository.MovieRepository
import com.leomarkpaway.movieapp.data.source.remote.entity.Movie
import com.leomarkpaway.movieapp.presentation.home.state.HomeState
import com.leomarkpaway.movieapp.presentation.home.state.UIComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ContainerHost<HomeState, UIComponent>, ViewModel() {

    override val container = container<HomeState, UIComponent>(HomeState())

    val nowShowingLiveData = MutableLiveData<List<Movie>>()
    val errorLiveData = MutableLiveData<String>()

    //live data to read room database
    val genresLiveData = liveData(Dispatchers.IO) {
        emitSource(movieRepository.getGenres())
    }
    val myWatchlist = liveData(Dispatchers.IO) {
        emitSource(movieRepository.getMyWatchlist())
    }

    init {
        // TODO use intent
        intent {
            val nowShowingMovies = movieRepository.getNowShowing()
            nowShowingMovies.collectLatest { movies ->
                reduce {
                    state.copy(nowShowingMovies = movies)
                }
            }
        }

        viewModelScope.launch {
            movieRepository.getNowShowing().collect { movies ->
                    if (movies.isNotEmpty()) {
                        nowShowingLiveData.value = movies
                    } else {
                        errorLiveData.value = "Failed to load movies"
                    }
                }
            movieRepository.fetchAndSaveGenresToDatabase().collect { }
        }
        viewModelScope.launch(Dispatchers.IO) {
            movieRepository.getMyWatchlist()
        }
    }

    fun addToMyWatchlist(movie: Movie) = viewModelScope.launch(Dispatchers.IO) {
        movieRepository.addToMyWatchlist(movie)
    }

    fun removeFromMyWatchlist(movie: Movie) = viewModelScope.launch(Dispatchers.IO) {
        movieRepository.removeFromMyWatchlist(movie)
    }

}