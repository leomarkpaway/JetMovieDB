package com.leomarkpaway.movieapp.common.util

import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

inline fun <reified T>provideApi(apiUrl: String, httpClient: OkHttpClient): T {
    return Retrofit.Builder()
        .baseUrl(apiUrl)
        .addConverterFactory(MoshiConverterFactory.create().asLenient())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .client(httpClient)
        .build()
        .create(T::class.java)
}