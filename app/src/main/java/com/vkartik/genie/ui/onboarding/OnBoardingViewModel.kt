package com.vkartik.genie.ui.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vkartik.genie.domain.service.AccountService
import com.vkartik.genie.domain.service.LogService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    accountService: AccountService
): ViewModel() {
    init {
        viewModelScope.launch(Dispatchers.IO) {
            accountService.createAnonymousAccount()
        }
    }

    val uiState = accountService.currentUser.map {
        OnBoardingUiState(it.isAnonymous)
    }
}