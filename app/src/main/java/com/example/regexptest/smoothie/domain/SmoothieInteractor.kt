package com.example.regexptest.smoothie.domain

import android.util.Log
import com.example.regexptest.smoothie.data.SmoothieRepository

class SmoothieInteractor(
    private val appId: String,
    private val repository: SmoothieRepository,
) {
    fun doSomething() {
        Log.d("MYTAG", "AppId = $appId ; $this \n")
        repository.getSomething()
    }
}