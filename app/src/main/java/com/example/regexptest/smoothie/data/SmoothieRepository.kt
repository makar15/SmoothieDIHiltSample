package com.example.regexptest.smoothie.data

import android.util.Log

class SmoothieRepository(
    private val appId: String,
    private val appName: String,
    private val localSource: LocalSource,
    private val remoteSource: RemoteSource,
    private val retrofit: Retrofit,
) {
    fun getSomething() {
        Log.d("MYTAG", "AppId = $appId ; AppName = $appName ; $this \n")
        remoteSource.doAction()
        localSource.doAction()
        retrofit.getSomething()
    }
}