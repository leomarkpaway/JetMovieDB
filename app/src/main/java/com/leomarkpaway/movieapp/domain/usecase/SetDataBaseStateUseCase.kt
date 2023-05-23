package com.leomarkpaway.movieapp.domain.usecase

import com.leomarkpaway.movieapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SetDataBaseStateUseCase @Inject constructor(private val repository: MovieRepository) {
    operator fun invoke(isDataBaseEmpty: Boolean): Flow<Unit> = flow {
        try {emit(repository.setDataBaseState(isDataBaseEmpty))
        } catch (e: Exception) { e.printStackTrace() }
    }
}