package com.vkartik.genie.domain.service

import com.vkartik.genie.domain.model.Account
import kotlinx.coroutines.flow.Flow

interface StorageService {
    val accounts: Flow<List<Account>>

    suspend fun getAccount(accountId: String): Account?

    suspend fun save(account: Account): String
    suspend fun update(account: Account)
    suspend fun delete(accountId: String)
}
