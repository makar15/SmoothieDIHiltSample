package com.example.regexptest.calendar.di

import com.example.regexptest.smoothie.di.singleton.SmoothieSingletonEntryPoint
import dagger.Lazy
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SetUpCalendarSdk @Inject constructor(
    @CalendarApp private val smoothieSingletonEntryPoint: Lazy<SmoothieSingletonEntryPoint>,
) {

    fun init() {
        smoothieSingletonEntryPoint.get()
    }
}