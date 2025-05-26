package com.example.regexptest.smoothie.di.singleton

import com.example.regexptest.smoothie.data.Retrofit
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class SingletonModule {

    @Provides
    @Reusable
    fun provideRetrofit(
        @AppID appId: String,
    ): Retrofit {
        return Retrofit(
            appId = appId,
        )
    }
}