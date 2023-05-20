package com.vkartik.genie.data.repository

import com.vkartik.genie.data.AccountDAO
import com.vkartik.genie.data.mapper.AccountMapper
import com.vkartik.genie.domain.model.Account
import com.vkartik.genie.domain.repository.AccountRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OfflineAccountRepository @Inject constructor(private val accountDAO: AccountDAO, private val mapper: AccountMapper):
    AccountRepository {
    override fun getAllAccountsStream(): Flow<List<Account>> {
        return accountDAO.getAllAccounts().map {
            entityList -> mapper.entityListToAccountList(entityList)
        }
    }

    override fun getAccountStream(id: Int): Flow<Account> {
        return accountDAO.getAccount(id).map { mapper.mapFromEntity(it) }
    }

    override suspend fun insertAccount(account: Account) {
        accountDAO.insert(mapper.mapToEntity(account))
    }

    override suspend fun updateAccount(account: Account) {
        accountDAO.update(mapper.mapToEntity(account))
    }

    override suspend fun deleteAccount(account: Account) {
        accountDAO.delete(mapper.mapToEntity(account))
    }
}