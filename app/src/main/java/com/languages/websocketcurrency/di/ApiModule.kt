package com.languages.websocketcurrency.di

import com.languages.websocketcurrency.net.NetService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApiModule {

    @Singleton
    @Provides
    fun getApi() = NetService.getApi()
}