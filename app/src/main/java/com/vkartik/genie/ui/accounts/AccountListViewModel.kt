package com.vkartik.genie.ui.accounts

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vkartik.genie.domain.repository.AccountRepository
import com.vkartik.genie.domain.model.Account
import com.vkartik.genie.domain.use_cases.DeleteAccountUseCase
import com.vkartik.genie.domain.use_cases.GetAllAccountsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountsViewModel @Inject constructor(
    getAllAccountsUseCase: GetAllAccountsUseCase,
    private val deleteAccountUseCase: DeleteAccountUseCase
) : ViewModel() {
    val accountsUiState: StateFlow<AccountsUiState> =
        getAllAccountsUseCase().map { AccountsUiState(it) }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = AccountsUiState()
        )

    private val _showDeleteConfirmationDialog = MutableLiveData(false)
    val showDeleteConfirmationDialog: LiveData<Boolean> get() = _showDeleteConfirmationDialog

    fun onEntryDelete() {
        _showDeleteConfirmationDialog.value = true
    }

    fun onDeleteDialogDismissed() {
        _showDeleteConfirmationDialog.value = false
    }

    fun performDeleteAction(account: Account) {
        Log.e("kartikk", "delete performed")
        viewModelScope.launch(Dispatchers.IO) { deleteAccountUseCase(account) }
    }
}

data class AccountsUiState(val accountsList: List<Account> = listOf())