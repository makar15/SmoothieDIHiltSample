package com.example.regexptest.notes.di

import com.example.regexptest.smoothie.di.singleton.SmoothieSingletonEntryPoint
import dagger.Lazy
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SetUpNotesSdk @Inject constructor(
    @NotesApp private val smoothieSingletonEntryPoint: Lazy<SmoothieSingletonEntryPoint>,
) {

    fun init() {
        smoothieSingletonEntryPoint.get()
    }
}