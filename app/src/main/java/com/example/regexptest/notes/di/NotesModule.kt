package com.example.regexptest.notes.di

import com.example.regexptest.notes.data.RoomLocalSource
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
class NotesModule {

    @Provides
    @Singleton
    @NotesApp
    fun provideSmoothieSingletonEntryPoint(
        @NotesApp appId: String,
        @NotesApp localSource: LocalSource,
        @NotesApp authInteractor: AuthInteractor,
        singletonEntryPointBuilder: SmoothieSingletonComponentBuilder,
    ): SmoothieSingletonEntryPoint {
        return SmoothieSingletonComponent.getOrCreateComponent(
            appId = appId,
            appName = "N",
            localSource = localSource,
            authInteractor = authInteractor,
            builder = singletonEntryPointBuilder,
        )
    }

    @Provides
    @Singleton
    @NotesApp
    fun provideRoomLocalSource(@NotesApp appId: String): LocalSource {
        return RoomLocalSource(appId)
    }

    @Provides
    @Singleton
    @NotesApp
    fun provideAppId(): String {
        return "Notes"
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NotesApp