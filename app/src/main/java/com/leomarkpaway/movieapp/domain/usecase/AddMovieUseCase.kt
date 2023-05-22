package com.leomarkpaway.movieapp.domain.usecase

import com.leomarkpaway.movieapp.data.source.local.entity.Movie
import com.leomarkpaway.movieapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddMovieUseCase @Inject constructor(private val repository: MovieRepository) {
    operator fun invoke(movie: Movie): Flow<Unit> = flow {
        try { emit(repository.addMovie(movie)) }
        catch (e: Exception) { e.printStackTrace() }
    }
}