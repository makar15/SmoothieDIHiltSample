package com.example.regexptest.smoothie.data

import android.util.Log

class SmoothieRepository(
    private val appId: String
) {
    fun getSomething(){
        Log.d("MYTAG", "Repository get something with $appId")
    }
}