package com.languages.websocketcurrency.di

import android.content.Context
import com.languages.websocketcurrency.data.repository.AuthRepositoryImpl
import com.languages.websocketcurrency.data.repository.GetInstrumentsDataRepositoryImpl
import com.languages.websocketcurrency.domain.repository.AuthRepository
import com.languages.websocketcurrency.domain.repository.GetInstrumentsDataRepository
import com.languages.websocketcurrency.net.Api
import com.languages.websocketcurrency.net.NetService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface AppModule {

//    @Singleton
//    @Provides
//    fun providePreferencesStorage(@ApplicationContext context: Context) =
//        PreferenceDataStoreManager(context.datastore)

    @Binds
    fun bindGetInstrumentsDataRepository(impl: GetInstrumentsDataRepositoryImpl): GetInstrumentsDataRepository

    @Binds
    fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository
}