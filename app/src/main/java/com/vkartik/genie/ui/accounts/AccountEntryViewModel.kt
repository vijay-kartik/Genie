package com.vkartik.genie.ui.accounts

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.vkartik.genie.domain.AccountRepository

class AccountEntryViewModel(private val accountRepository: AccountRepository): ViewModel() {
    var accountUiState: AccountUiState by mutableStateOf(AccountUiState())
    suspend fun saveAccount() {
        if(accountUiState.isValid()) {
            accountRepository.insertAccount(accountUiState.toAccount())
        }
    }

    fun updateUiState(newUiState: AccountUiState) {
        accountUiState = newUiState
    }
}