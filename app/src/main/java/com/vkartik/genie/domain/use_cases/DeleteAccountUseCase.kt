package com.vkartik.genie.domain.use_cases

import com.vkartik.genie.domain.model.Account
import com.vkartik.genie.domain.repository.AccountRepository
import javax.inject.Inject

class DeleteAccountUseCase @Inject constructor(private val accountRepository: AccountRepository) {
    suspend operator fun invoke(account: Account) {
        accountRepository.deleteAccount(account)
    }

}