package com.vkartik.genie

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vkartik.genie.domain.service.AccountService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.properties.Delegates

@HiltViewModel
class MainActivityViewModel @Inject constructor(accountService: AccountService, sharedPreferences: SharedPreferences) :
    ViewModel() {
    var setupComplete by Delegates.notNull<Boolean>()

    init {
            viewModelScope.launch {
                if(!accountService.hasUser) {
                    accountService.createAnonymousAccount()
                }
            }
            setupComplete = sharedPreferences.getBoolean("setup_complete", false)
        }
    val uiState: StateFlow<MainActivityUiState> = accountService.currentUser.map {
        if (it.isAnonymous || it.isRealUser) MainActivityUiState.Success else {
            MainActivityUiState.Loading
        }
    }.stateIn(
        scope = viewModelScope,
        initialValue = MainActivityUiState.Loading,
        started = SharingStarted.WhileSubscribed(5_000),
    )
}

sealed interface MainActivityUiState {
    object Loading : MainActivityUiState
    object Success : MainActivityUiState
}