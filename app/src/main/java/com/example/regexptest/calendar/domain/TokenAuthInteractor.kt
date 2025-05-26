package com.example.regexptest.calendar.domain

import android.util.Log
import com.example.regexptest.smoothie.domain.AuthInteractor

class TokenAuthInteractor(private val appId: String) : AuthInteractor {

    override fun doSomething() {
        Log.d("MYTAG", "AppId = $appId ; $this \n")
    }
}