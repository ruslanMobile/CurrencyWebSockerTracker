package com.languages.websocketcurrency.di

import android.content.Context
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.preferencesDataStore
import com.languages.websocketcurrency.utils.PreferenceDataStoreManager
import com.languages.websocketcurrency.utils.PreferenceDataStoreManager.Keys.PREF_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DBMobule {
    val Context.datastore by preferencesDataStore(
        name = PREF_NAME,
        produceMigrations = { context ->
            listOf(
                SharedPreferencesMigration(
                    context,
                    PREF_NAME
                )
            )
        }
    )

    @Singleton
    @Provides
    fun providePreferencesStorage(@ApplicationContext context: Context) =
        PreferenceDataStoreManager(context.datastore)
}