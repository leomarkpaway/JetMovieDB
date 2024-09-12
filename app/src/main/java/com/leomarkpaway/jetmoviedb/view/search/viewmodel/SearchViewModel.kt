package com.leomarkpaway.jetmoviedb.view.search.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leomarkpaway.jetmoviedb.data.repository.MovieRepository
import com.leomarkpaway.jetmoviedb.data.source.remote.entity.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _searchResults = MutableStateFlow<List<Movie>>(emptyList())
    val searchResults: StateFlow<List<Movie>> = _searchResults

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun search(query: String) {
        viewModelScope.launch {
            _isLoading.value = true
            movieRepository.searchMovie(query)
                .flowOn(Dispatchers.IO)
                .collect { movies ->
                    _searchResults.value = movies
                    delay(500)
                    _isLoading.value = false
                }
        }
    }

    fun resetSearchResults() = viewModelScope.launch { _searchResults.emit(emptyList()) }

}