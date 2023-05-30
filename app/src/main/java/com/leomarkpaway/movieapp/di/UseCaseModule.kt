package com.leomarkpaway.movieapp.di

import com.leomarkpaway.movieapp.domain.usecase.GetAllMovieUseCase
import com.leomarkpaway.movieapp.domain.usecase.AddToWatchListUseCase
import com.leomarkpaway.movieapp.domain.repository.Repository
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
    fun provideGetMoviesUseCase(repository: Repository) = GetAllMovieUseCase(repository)

    @Provides
    @Singleton
    fun provideToggleWatchlistUseCase(repository: Repository) = AddToWatchListUseCase(repository)

}