package com.leomarkpaway.movieapp.di

import com.leomarkpaway.movieapp.data.source.remote.service.ApiService
import com.leomarkpaway.movieapp.data.source.repository.RepositoryImpl
import com.leomarkpaway.movieapp.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMoviesRepository(api: ApiService): Repository {
        return RepositoryImpl(api)
    }

}