package com.example.regexptest.notes.di

import com.example.regexptest.notes.domain.CookieAuthInteractor
import com.example.regexptest.smoothie.domain.AuthInteractor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NotesViewModelModule {

    @Provides
    @Singleton
    @NotesApp
    fun provideTokenAuthInteractor(
        @NotesApp appId: String,
    ): AuthInteractor {
        return CookieAuthInteractor(appId)
    }
}
