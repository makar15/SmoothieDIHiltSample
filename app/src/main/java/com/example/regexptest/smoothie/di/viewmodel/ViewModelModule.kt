package com.example.regexptest.smoothie.di.viewmodel

import com.example.regexptest.smoothie.di.singleton.SmoothieSingletonEntryPoint
import com.example.regexptest.smoothie.domain.SmoothieInteractor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn

@Module
@InstallIn(SmoothieViewModelComponent::class)
class ViewModelModule {

    @Provides
    @SmoothieViewModel
    fun provideInteractor(
        dependencies: SmoothieSingletonEntryPoint,
    ): SmoothieInteractor {
        return SmoothieInteractor(dependencies.appId(), dependencies.repository())
    }
}