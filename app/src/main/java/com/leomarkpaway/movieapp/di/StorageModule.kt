package com.leomarkpaway.movieapp.di

import android.content.Context
import com.leomarkpaway.movieapp.data.source.local.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StorageModule {

    @Provides
    @Singleton
    fun providePreferenceManager(@ApplicationContext appContext: Context): PreferenceManager {
        return PreferenceManager(appContext, "preference_name")
    }

}