package com.example.regexptest.smoothie.di.singleton

import com.example.regexptest.smoothie.data.LocalSource
import com.example.regexptest.smoothie.data.SmoothieRepository
import com.example.regexptest.smoothie.domain.AuthInteractor
import dagger.BindsInstance
import dagger.hilt.DefineComponent
import dagger.hilt.EntryPoint
import dagger.hilt.EntryPoints
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Scope

@Scope
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class SmoothieSingleton

@DefineComponent(parent = SingletonComponent::class)
@SmoothieSingleton
interface SmoothieSingletonComponent {

    companion object {
        val components: HashMap<String, SmoothieSingletonEntryPoint> = hashMapOf()

        @Synchronized
        fun getOrCreateComponent(
            appId: String,
            appName: String,
            localSource: LocalSource,
            authInteractor: AuthInteractor,
            builder: SmoothieSingletonComponentBuilder,
        ): SmoothieSingletonEntryPoint {
            if (components[appId] == null) {
                components[appId] = EntryPoints.get(
                    builder
                        .appId(appId)
                        .appName(appName)
                        .localSource(localSource)
                        .authInteractor(authInteractor)
                        .build(),
                    SmoothieSingletonEntryPoint::class.java
                )
            }
            return components[appId]!!
        }

        @Synchronized
        fun getComponent(appId: String, ): SmoothieSingletonEntryPoint {
            return components[appId]!!
        }
    }
}

@DefineComponent.Builder
interface SmoothieSingletonComponentBuilder {
    fun appId(@BindsInstance @AppID appId: String): SmoothieSingletonComponentBuilder
    fun appName(@BindsInstance @Smoothie appName: String): SmoothieSingletonComponentBuilder
    fun localSource(@BindsInstance @Smoothie localSource: LocalSource): SmoothieSingletonComponentBuilder
    fun authInteractor(@BindsInstance @Smoothie authInteractor: AuthInteractor): SmoothieSingletonComponentBuilder
    fun build(): SmoothieSingletonComponent
}

@EntryPoint
@InstallIn(SmoothieSingletonComponent::class)
interface SmoothieSingletonEntryPoint {
    @AppID
    fun appId(): String
    @Smoothie
    fun appName(): String
    @Smoothie
    fun localSource(): LocalSource
    @Smoothie
    fun authInteractor(): AuthInteractor
    fun repository(): SmoothieRepository
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Smoothie

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class AppID