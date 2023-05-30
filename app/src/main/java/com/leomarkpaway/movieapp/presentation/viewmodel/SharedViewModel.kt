package com.leomarkpaway.movieapp.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.leomarkpaway.movieapp.domain.usecase.GetAllMovieUseCase
import com.leomarkpaway.movieapp.domain.usecase.AddToWatchListUseCase
import com.leomarkpaway.movieapp.common.base.BaseViewModel
import com.leomarkpaway.movieapp.data.source.remote.dto.MovieItemDTO
import com.leomarkpaway.movieapp.data.source.remote.dto.MoviesDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.List

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val getAllMovieUseCase: GetAllMovieUseCase,
    private val addToWatchListUseCase: AddToWatchListUseCase,
) : BaseViewModel() {

    private val _allMovie = MutableStateFlow<List<MovieItemDTO>?>(null)
    val allMovie = _allMovie.asStateFlow()

    private val _isSortByDate = MutableStateFlow(false)
    val isSortByDate = _isSortByDate.asStateFlow()

    private val _currentMovieSelected = MutableStateFlow<MovieItemDTO?>(null)
    val currentMovieSelected = _currentMovieSelected.asStateFlow()

    private val _isOnWatchlist = MutableStateFlow<Boolean?>(null)
    val isOnWatchlist = _isOnWatchlist.asStateFlow()

    fun getAllMovies() {
        getAllMovieUseCase.invoke().map { movies ->
            launchDataOperation(flowOf(movies)) { data -> _allMovie.value = data.items }
        }.launchIn(viewModelScope)
    }

    fun updateIsSortByDate(boolean: Boolean) {
        launchDataOperation(flowOf(boolean)) { _isSortByDate.value = it }
    }

    fun setCurrentMovieSelected(movie: MovieItemDTO) {
        launchDataOperation(flowOf(movie)) { data -> _currentMovieSelected.value = data }
    }

    fun updateIsOnWatchlist(isOnWatchlist: Boolean) {
        launchDataOperation(flowOf(isOnWatchlist)) {boolean -> _isOnWatchlist.value = boolean}
    }

    fun addToWatchList(id: Int) = addToWatchListUseCase(id).launchIn(viewModelScope)

    fun sortMoviesByTitle() {
        val newMovieList = _allMovie.value?.sortedBy { it.title } ?: emptyList()
        launchDataOperation(flowOf(newMovieList)) {data -> _allMovie.value = data}
    }

    fun sortMoviesByDate() {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val newMovieList =
            _allMovie.value?.sortedBy { dateFormat.parse(it.releaseDate) } ?: emptyList()
        launchDataOperation(flowOf(newMovieList)) {data -> _allMovie.value = data}
    }

}