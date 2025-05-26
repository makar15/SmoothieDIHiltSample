package com.example.regexptest.smoothie.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.regexptest.smoothie.di.singleton.SmoothieSingletonEntryPoint
import dagger.assisted.AssistedFactory

@AssistedFactory
interface SmoothieViewModelFactory {
    fun create(singletonDependencies: SmoothieSingletonEntryPoint): SmoothieViewModel
}

object SmoothieViewModelProvider {

    fun provideFactory(
        assistedFactory: SmoothieViewModelFactory,
        singletonDependencies: SmoothieSingletonEntryPoint,
    ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return assistedFactory.create(singletonDependencies) as T
        }
    }
}