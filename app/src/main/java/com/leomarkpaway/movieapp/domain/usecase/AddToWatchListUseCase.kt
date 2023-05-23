package com.leomarkpaway.movieapp.domain.usecase

import com.leomarkpaway.movieapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddToWatchListUseCase  @Inject constructor(private val repository: MovieRepository) {
    operator fun invoke(id: Long, isOnWatchlist: Boolean): Flow<Unit> = flow {
        try { emit(repository.addToWatchList(id, isOnWatchlist))
        } catch (e: Exception) { e.printStackTrace() }
    }
}