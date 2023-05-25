package com.vkartik.genie.di

import android.content.Context
import android.content.SharedPreferences
import com.vkartik.genie.ui.intro.setupEncryptedSharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object SharedPreferencesModule {
    @Provides
    fun providesSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return setupEncryptedSharedPreferences(context)
    }
}