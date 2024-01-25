package com.example.regexptest.smoothie.presentation

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.regexptest.smoothie.di.singleton.SmoothieSingletonComponent
import com.example.regexptest.smoothie.di.singleton.SmoothieSingletonComponentBuilder
import javax.inject.Inject

abstract class SmoothieFragment : Fragment() {

    abstract val appId: String

    @Inject
    lateinit var viewModelFactory: SmoothieViewModel.SmoothieViewModelFactory

    @Inject
    lateinit var singletonEntryPointBuilder: SmoothieSingletonComponentBuilder

    private val smoothieViewModel: SmoothieViewModel by viewModels {
        SmoothieViewModel.provideFactory(
            viewModelFactory,
            SmoothieSingletonComponent.getOrCreateComponent(appId, singletonEntryPointBuilder)
        )
    }

    override fun onStart() {
        super.onStart()
        Log.d(
            "MYTAG",
            "doSomething with ${smoothieViewModel.component?.singletonDependencies()?.appId()}"
        )
        smoothieViewModel.doSomething()
    }
}