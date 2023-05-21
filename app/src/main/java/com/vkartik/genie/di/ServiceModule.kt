package com.vkartik.genie.di

import com.vkartik.genie.domain.service.AccountService
import com.vkartik.genie.domain.service.LogService
import com.vkartik.genie.data.service.AccountServiceImpl
import com.vkartik.genie.data.service.LogServiceImpl
import com.vkartik.genie.data.service.StorageServiceImpl
import com.vkartik.genie.domain.service.StorageService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {
    @Binds abstract fun providesAccountService(impl: AccountServiceImpl): AccountService

    @Binds abstract fun provideLogService(impl: LogServiceImpl): LogService

    @Binds abstract fun providesStorageService(impl: StorageServiceImpl): StorageService

}