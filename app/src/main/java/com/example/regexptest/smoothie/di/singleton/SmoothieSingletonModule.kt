package com.example.regexptest.smoothie.di.singleton

import com.example.regexptest.smoothie.data.LocalSource
import com.example.regexptest.smoothie.data.RemoteSource
import com.example.regexptest.smoothie.data.Retrofit
import com.example.regexptest.smoothie.data.SmoothieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn

@Module
@InstallIn(SmoothieSingletonComponent::class)
class SmoothieSingletonModule {

    @Provides
    @SmoothieSingleton
    fun provideSmoothieRepository(
        @AppID appId: String,
        @Smoothie appName: String,
        @Smoothie localSource: LocalSource,
        remoteSource: RemoteSource,
        retrofit: Retrofit
    ): SmoothieRepository {
        return SmoothieRepository(
            appId = appId,
            appName= appName,
            localSource = localSource,
            remoteSource = remoteSource,
            retrofit = retrofit,
        )
    }
}