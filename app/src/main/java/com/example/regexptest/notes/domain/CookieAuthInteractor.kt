package com.example.regexptest.notes.domain

import android.util.Log
import com.example.regexptest.smoothie.domain.AuthInteractor

class CookieAuthInteractor(private val appId: String) : AuthInteractor {

    override fun doSomething() {
        Log.d("MYTAG", "AppId = $appId ; $this \n")
    }
}