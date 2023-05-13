package com.vkartik.genie.domain.model.service.module

import com.vkartik.genie.domain.model.service.AccountService
import com.vkartik.genie.domain.model.service.LogService
import com.vkartik.genie.domain.model.service.impl.AccountServiceImpl
import com.vkartik.genie.domain.model.service.impl.LogServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {
    @Binds abstract fun providesAccountService(impl: AccountServiceImpl): AccountService

    @Binds abstract fun provideLogService(impl: LogServiceImpl): LogService

}