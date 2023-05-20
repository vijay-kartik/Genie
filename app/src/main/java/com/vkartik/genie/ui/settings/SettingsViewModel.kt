package com.vkartik.genie.ui.settings

import androidx.lifecycle.ViewModel
import com.vkartik.genie.domain.service.AccountService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    accountService: AccountService
): ViewModel() {
    val uiState = accountService.currentUser.map {
        SettingsUiState(it.isAnonymous, it.isRealUser)
    }
}