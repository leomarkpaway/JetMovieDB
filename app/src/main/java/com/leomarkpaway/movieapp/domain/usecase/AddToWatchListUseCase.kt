package com.leomarkpaway.movieapp.domain.usecase

import com.leomarkpaway.movieapp.data.source.remote.dto.MovieItemDTO
import com.leomarkpaway.movieapp.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AddToWatchListUseCase @Inject constructor(private val repository: Repository) {
    operator fun invoke(id: Int): Flow<MovieItemDTO> = flow {
        try {
            emit(repository.toggleWatchlist(id))
        } catch (e: HttpException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

}