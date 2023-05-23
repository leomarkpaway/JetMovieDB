package com.leomarkpaway.movieapp.domain.usecase

import com.leomarkpaway.movieapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetDataBaseStateUseCase @Inject constructor(private val repository: MovieRepository) {
    operator fun invoke(): Flow<Boolean?> = flow {
        try {emit(repository.getDataBaseState())
        } catch (e: Exception) { e.printStackTrace() }
    }
}