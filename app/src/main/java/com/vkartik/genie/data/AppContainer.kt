package com.vkartik.genie.data

import android.content.Context
import com.vkartik.genie.domain.AccountRepository

interface AppContainer {
    val accountRepository: AccountRepository
}

class AppDataContainer(private val context: Context): AppContainer {
    override val accountRepository: AccountRepository by lazy {
        OfflineAccountRepository(AccountDatabase.getDatabase(context).accountDao())
    }
}