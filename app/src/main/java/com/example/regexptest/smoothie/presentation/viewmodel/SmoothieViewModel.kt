package com.example.regexptest.smoothie.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.regexptest.smoothie.data.Retrofit
import com.example.regexptest.smoothie.di.singleton.SmoothieSingletonEntryPoint
import com.example.regexptest.smoothie.di.viewmodel.SmoothieViewModelComponentBuilder
import com.example.regexptest.smoothie.di.viewmodel.SmoothieViewModelEntryPoint
import com.example.regexptest.smoothie.domain.AuthInteractor
import com.example.regexptest.smoothie.domain.SmoothieInteractor
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.EntryPoints

class SmoothieViewModel @AssistedInject constructor(
    builder: SmoothieViewModelComponentBuilder,
    @Assisted singletonDependencies: SmoothieSingletonEntryPoint,
) : ViewModel() {

    var component: SmoothieViewModelEntryPoint?

    private val appId: String
    private val smoothieInteractor: SmoothieInteractor
    private val authInteractor: AuthInteractor

    init {
        EntryPoints.get(
            builder
                .singletonDependencies(singletonDependencies)
                .authInteractor(singletonDependencies.authInteractor()).
                build(),
            SmoothieViewModelEntryPoint::class.java
        ).let { component ->
            this.component = component
            this.smoothieInteractor = component.interactor()
            this.appId = component.singletonDependencies().appId()
            this.authInteractor = component.authInteractor()
        }
    }

    fun doSomething() {
        Log.d("MYTAG", "AppId = $appId ; $this \n")
        smoothieInteractor.doSomething()
        authInteractor.doSomething()
    }

    override fun onCleared() {
        component = null
        super.onCleared()
    }
}