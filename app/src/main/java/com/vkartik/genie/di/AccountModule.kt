package com.vkartik.genie.di

import com.vkartik.genie.data.repository.OfflineAccountRepository
import com.vkartik.genie.domain.repository.AccountRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class AccountModule {
    @Binds abstract fun providesAccountRepository(impl: OfflineAccountRepository): AccountRepository
}