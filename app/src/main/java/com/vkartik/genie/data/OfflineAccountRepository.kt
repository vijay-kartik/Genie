package com.vkartik.genie.data

import com.vkartik.genie.domain.AccountRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OfflineAccountRepository @Inject constructor(private val accountDAO: AccountDAO): AccountRepository {
    override fun getAllAccountsStream(): Flow<List<Account>> {
        return accountDAO.getAllAccounts()
    }

    override fun getAccountStream(id: Int): Flow<Account> {
        return accountDAO.getAccount(id)
    }

    override suspend fun insertAccount(account: Account) {
        accountDAO.insert(account)
    }

    override suspend fun updateAccount(account: Account) {
        accountDAO.update(account)
    }

    override suspend fun deleteAccount(account: Account) {
        accountDAO.delete(account)
    }
}