package com.vkartik.genie.ui.accounts

import androidx.lifecycle.ViewModel
import com.vkartik.genie.data.Account

class AccountsViewModel: ViewModel()

data class AccountsUiState(val accountsList: List<Account> = listOf())