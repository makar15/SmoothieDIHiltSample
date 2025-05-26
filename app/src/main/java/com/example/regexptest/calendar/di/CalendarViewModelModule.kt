package com.example.regexptest.calendar.di

import com.example.regexptest.calendar.domain.TokenAuthInteractor
import com.example.regexptest.smoothie.domain.AuthInteractor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CalendarViewModelModule {

    @Provides
    @Singleton
    @CalendarApp
    fun provideTokenAuthInteractor(
        @CalendarApp appId: String,
    ): AuthInteractor {
        return TokenAuthInteractor(appId)
    }
}
