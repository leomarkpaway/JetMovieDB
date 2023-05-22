package com.leomarkpaway.movieapp.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.leomarkpaway.movieapp.common.base.BaseViewModel
import com.leomarkpaway.movieapp.data.source.local.entity.Movie
import com.leomarkpaway.movieapp.domain.usecase.AddMovieUseCase
import com.leomarkpaway.movieapp.domain.usecase.GetAllMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val addMovieUseCase: AddMovieUseCase,
    private val getAllMovieUseCase: GetAllMovieUseCase
) : BaseViewModel() {

    private val _allMovie = MutableStateFlow<List<Movie>?>(null)
    val allMovie = _allMovie.asStateFlow()

    fun addMovie(movie: Movie) = addMovieUseCase(movie).launchIn(viewModelScope)

    fun getAllMovies() {
        getAllMovieUseCase.invoke().map { movies ->
            launchDataOperation(flow = flowOf(movies), onSuccess = {data -> _allMovie.value = data})
        }.launchIn(viewModelScope)
    }

}