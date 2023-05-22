package com.leomarkpaway.movieapp.di

import com.leomarkpaway.movieapp.domain.repository.MovieRepository
import com.leomarkpaway.movieapp.domain.usecase.AddMovieUseCase
import com.leomarkpaway.movieapp.domain.usecase.GetAllMovieUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetAllMovieUseCase(movieRepository: MovieRepository) = GetAllMovieUseCase(movieRepository)

    @Provides
    @Singleton
    fun provideAddMovieUseCase(movieRepository: MovieRepository) = AddMovieUseCase(movieRepository)

}