package com.example.regexptest.calendar.di

import com.example.regexptest.calendar.data.SharedPrefLocalSource
import com.example.regexptest.smoothie.di.singleton.SmoothieSingletonEntryPoint
import com.example.regexptest.smoothie.di.singleton.SmoothieSingletonComponentBuilder
import com.example.regexptest.smoothie.data.LocalSource
import com.example.regexptest.smoothie.di.singleton.SmoothieSingletonComponent
import com.example.regexptest.smoothie.domain.AuthInteractor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CalendarModule {

    @Provides
    @Singleton
    @CalendarApp
    fun provideSmoothieSingletonEntryPoint(
        @CalendarApp appId: String,
        @CalendarApp localSource: LocalSource,
        @CalendarApp authInteractor: AuthInteractor,
        singletonEntryPointBuilder: SmoothieSingletonComponentBuilder,
    ): SmoothieSingletonEntryPoint {
        return SmoothieSingletonComponent.getOrCreateComponent(
            appId = appId,
            appName = "C",
            localSource = localSource,
            authInteractor = authInteractor,
            builder = singletonEntryPointBuilder,
        )
    }

    @Provides
    @Singleton
    @CalendarApp
    fun provideSharedPrefLocalSource(@CalendarApp appId: String): LocalSource {
        return SharedPrefLocalSource(appId)
    }

    @Provides
    @Singleton
    @CalendarApp
    fun provideAppId(): String {
        return "Calendar"
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CalendarApp