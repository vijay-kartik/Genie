package com.vkartik.genie.domain.use_cases

import com.vkartik.genie.domain.model.Account
import com.vkartik.genie.domain.repository.AccountRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllAccountsUseCase @Inject constructor(private val accountRepository: AccountRepository) {
    operator fun invoke(): Flow<List<Account>> {
        return accountRepository.getAllAccountsStream()
    }
}