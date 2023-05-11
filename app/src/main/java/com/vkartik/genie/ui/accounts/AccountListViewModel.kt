package com.vkartik.genie.ui.accounts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vkartik.genie.data.Account
import com.vkartik.genie.domain.AccountRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class AccountsViewModel(accountRepository: AccountRepository) : ViewModel() {
    val accountsUiState: StateFlow<AccountsUiState> =
        accountRepository.getAllAccountsStream().map { AccountsUiState(it) }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = AccountsUiState()
        )
}

data class AccountsUiState(val accountsList: List<Account> = listOf())