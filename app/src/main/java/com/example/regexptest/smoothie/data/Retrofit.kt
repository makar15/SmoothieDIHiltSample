package com.example.regexptest.smoothie.data

import android.util.Log

class Retrofit(private val appId: String) {

    fun getSomething() {
        Log.d("MYTAG", "AppId = $appId ; $this \n")
    }
}