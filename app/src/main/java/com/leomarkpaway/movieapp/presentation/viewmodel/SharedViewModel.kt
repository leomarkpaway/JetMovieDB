package com.leomarkpaway.movieapp.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.leomarkpaway.movieapp.common.base.BaseViewModel
import com.leomarkpaway.movieapp.common.util.convertDateToMillis
import com.leomarkpaway.movieapp.data.source.local.entity.Movie
import com.leomarkpaway.movieapp.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val addMovieUseCase: AddMovieUseCase,
    private val getAllMovieUseCase: GetAllMovieUseCase,
    private val sortByDateUseCase: SortByDateUseCase,
    private val setDataBaseStateUseCase: SetDataBaseStateUseCase,
    private val getDataBaseStateUseCase: GetDataBaseStateUseCase
) : BaseViewModel() {

    private val _allMovie = MutableStateFlow<List<Movie>?>(null)
    val allMovie = _allMovie.asStateFlow()

    private val _isSortByDate = MutableStateFlow(false)
    val isSortByDate = _isSortByDate.asStateFlow()

    private val _isDataBaseEmpty = MutableStateFlow<Boolean?>(null)
    val isDataBaseEmpty = _isDataBaseEmpty.asStateFlow()

    fun addMovie() = addMovieUseCase(movies).launchIn(viewModelScope)

    fun getAllMovies() {
        getAllMovieUseCase.invoke().map { movies ->
            launchDataOperation(flowOf(movies)) {data -> _allMovie.value = data}
        }.launchIn(viewModelScope)
    }

    fun sortByDate() {
        sortByDateUseCase.invoke().map { movies ->
            launchDataOperation(flowOf(movies)) {data -> _allMovie.value = data}
        }.launchIn(viewModelScope)
    }

    fun updateIsSortByDate(boolean: Boolean) {
        launchDataOperation(flowOf(boolean)) { _isSortByDate.value = it }
    }

    fun setDataBaseState(isDataBaseEmpty: Boolean) {
        setDataBaseStateUseCase(isDataBaseEmpty).launchIn(viewModelScope)
    }

    fun getDataBaseState() {
        getDataBaseStateUseCase.invoke().map { dataBaseState ->
            launchDataOperation(flowOf(dataBaseState)) {state -> _isDataBaseEmpty.value = state}
        }.launchIn(viewModelScope)
    }

    companion object {
        val movies = listOf(
            Movie(
                title = "Tenet",
                description = "Armed with only one word, Tenet, and fighting for the survival of the entire world, a Protagonist journeys through a twilight world of international espionage on a mission that will unfold in something beyond real time.",
                rating = "7.8",
                duration = "2h 30 min",
                genre = "Action, Sci-Fi",
                released_date_millis = convertDateToMillis("3 September 2020"),
                trailer_link = "https://www.youtube.com/watch?v=LdOM0x0XDMo",
                image = "Tenet.png"
            ),
            Movie(
                title = "Spider-Man: Into the Spider-Verse",
                description = "Teen Miles Morales becomes the Spider-Man of his universe, and must join with five spider-powered individuals from other dimensions to stop a threat for all realities.",
                rating = "8.4",
                duration = "1h 57min",
                genre = "Action, Animation, Adventure",
                released_date_millis = convertDateToMillis("14 December 2018"),
                trailer_link = "https://www.youtube.com/watch?v=tg52up16eq0",
                image = "Spider Man.png"
            ),
            Movie(
                title = "Knives Out",
                description = "A detective investigates the death of a patriarch of an eccentric, combative family.",
                rating = "7.9",
                duration = "2h 10min",
                genre = "Comedy, Crime, Drama",
                released_date_millis = convertDateToMillis("27 November 2019"),
                trailer_link = "https://www.youtube.com/watch?v=qGqiHJTsRkQ",
                image = "Knives Out.png"
            ),
            Movie(
                title = "Guardians of the Galaxy",
                description = "A group of intergalactic criminals must pull together to stop a fanatical warrior with plans to purge the universe.",
                rating = "8.0",
                duration = "2h 1min",
                genre = "Action, Adventure, Comedy",
                released_date_millis = convertDateToMillis("1 August 2014"),
                trailer_link = "https://www.youtube.com/watch?v=d96cjJhvlMA",
                image = "Guardians of The Galaxy.png"
            ),
            Movie(
                title = "Avengers: Age of Ultron",
                description = "When Tony Stark and Bruce Banner try to jump-start a dormant peacekeeping program called Ultron, things go horribly wrong and it's up to Earth's mightiest heroes to stop the villainous Ultron from enacting his terrible plan. ",
                rating = "7.3",
                duration = "2h 21min",
                genre = "Action, Adventure, Sci-Fi",
                released_date_millis = convertDateToMillis("1 May 2015"),
                trailer_link = "https://www.youtube.com/watch?v=tmeOjFno6Do",
                image = "Avengers.png"
            )
        )
    }

}