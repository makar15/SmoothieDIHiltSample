package com.example.regexptest.smoothie.di.singleton

import com.example.regexptest.smoothie.data.SmoothieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn

@Module
@InstallIn(SmoothieSingletonComponent::class)
class SingletonModule {

    @Provides
    @SmoothieSingleton
    fun provideRepository(appId: String): SmoothieRepository {
        return SmoothieRepository(appId)
    }
}
