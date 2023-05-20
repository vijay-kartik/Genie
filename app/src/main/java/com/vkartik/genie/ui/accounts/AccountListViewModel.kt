package com.vkartik.genie.ui.accounts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vkartik.genie.domain.repository.AccountRepository
import com.vkartik.genie.domain.model.Account
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class AccountsViewModel @Inject constructor(accountRepository: AccountRepository) : ViewModel() {
    val accountsUiState: StateFlow<AccountsUiState> =
        accountRepository.getAllAccountsStream().map { AccountsUiState(it) }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = AccountsUiState()
        )
}

data class AccountsUiState(val accountsList: List<Account> = listOf())