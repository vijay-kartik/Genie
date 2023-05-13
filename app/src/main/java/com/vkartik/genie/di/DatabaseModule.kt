package com.vkartik.genie.di

import android.content.Context
import com.vkartik.genie.data.AccountDAO
import com.vkartik.genie.data.AccountDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides fun accountDatabase(@ApplicationContext context: Context): AccountDatabase = AccountDatabase.getDatabase(context)

    @Provides fun accountDao(db: AccountDatabase): AccountDAO = db.accountDao()
}