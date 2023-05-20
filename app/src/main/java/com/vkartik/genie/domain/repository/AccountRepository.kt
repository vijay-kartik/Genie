package com.vkartik.genie.domain.repository

import com.vkartik.genie.domain.model.Account
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    fun getAllAccountsStream(): Flow<List<Account>>
    fun getAccountStream(id: Int): Flow<Account>
    suspend fun insertAccount(account: Account)
    suspend fun updateAccount(account: Account)
    suspend fun deleteAccount(account: Account)
}