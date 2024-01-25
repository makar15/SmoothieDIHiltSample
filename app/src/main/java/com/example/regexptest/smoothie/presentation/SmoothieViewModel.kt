package com.example.regexptest.smoothie.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.regexptest.smoothie.di.singleton.CustomSingletonEntryPoint
import com.example.regexptest.smoothie.di.viewmodel.CustomViewModelEntryPoint
import com.example.regexptest.smoothie.di.viewmodel.SmoothieViewModelComponentBuilder
import com.example.regexptest.smoothie.domain.SmoothieInteractor
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.EntryPoints

class SmoothieViewModel @AssistedInject constructor(
    builder: SmoothieViewModelComponentBuilder,
    @Assisted singletonDependencies: CustomSingletonEntryPoint,
) : ViewModel() {
    var component: CustomViewModelEntryPoint?
    private val interactor: SmoothieInteractor
    private val appId: String

    init {
        EntryPoints.get(
            builder.singletonDependencies(singletonDependencies).build(),
            CustomViewModelEntryPoint::class.java
        ).let { component ->
            this.component = component
            this.interactor = component.interactor()
            this.appId = component.singletonDependencies().appId()
        }
    }

    fun doSomething() {
        Log.d("MYTAG", "DoSomethingOnViewModel with $appId $interactor")
        interactor.doSomething()
    }

    @AssistedFactory
    interface SmoothieViewModelFactory {
        fun create(singletonDependencies: CustomSingletonEntryPoint): SmoothieViewModel
    }

    companion object {
        fun provideFactory(
            assistedFactory: SmoothieViewModelFactory,
            singletonDependencies: CustomSingletonEntryPoint,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(singletonDependencies) as T
            }
        }
    }

    override fun onCleared() {
        component = null
        super.onCleared()
    }

}