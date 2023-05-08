package com.vkartik.genie.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.vkartik.genie.GenieApplication
import com.vkartik.genie.ui.accounts.AccountEntryViewModel
import com.vkartik.genie.ui.accounts.AccountsViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            AccountEntryViewModel(genieApplication().container.accountRepository)
        }
        initializer { AccountsViewModel() }
    }
}

fun CreationExtras.genieApplication(): GenieApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as GenieApplication)