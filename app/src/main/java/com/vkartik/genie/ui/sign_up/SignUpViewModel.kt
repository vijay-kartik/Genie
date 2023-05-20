package com.vkartik.genie.ui.sign_up

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.makeitso.common.ext.isValidEmail
import com.example.makeitso.common.ext.isValidPassword
import com.example.makeitso.common.ext.passwordMatches
import com.vkartik.genie.GenieViewModel
import com.vkartik.genie.R
import com.vkartik.genie.domain.model.service.AccountService
import com.vkartik.genie.domain.model.service.LogService
import com.vkartik.genie.ui.navigation.AccountsDestination
import com.vkartik.genie.ui.onboarding.OnBoardingDestination
import com.vkartik.genie.ui.snackbar.SnackbarManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val accountService: AccountService,
    logService: LogService
) : GenieViewModel(logService) {
    var uiState = mutableStateOf(SignUpUiState())
        private set

    private val email
        get() = uiState.value.email
    private val password
        get() = uiState.value.password

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun onRepeatPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(repeatPassword = newValue)
    }

    fun onSignUpClick(openAndPopUp: (String, String) -> Unit) {
        Log.e("Kartik", "open and popup")
        if (!email.isValidEmail()) {
            SnackbarManager.showMessage(R.string.email_error)
            Log.e("Kartik", "open and popup1")
            return
        }

        if (!password.isValidPassword()) {
            SnackbarManager.showMessage(R.string.password_error)
            Log.e("Kartik", "open and popup2")
            return
        }

        if (!password.passwordMatches(uiState.value.repeatPassword)) {
            SnackbarManager.showMessage(R.string.password_match_error)
            Log.e("Kartik", "open and popup3")
            return
        }

        Log.e("Kartik", "open and popup4")
        launchCatching {
            accountService.linkAccount(email, password)
            Log.e("Kartik", "open and popup")
            openAndPopUp(OnBoardingDestination.route, SignUpDestination.route)
        }
    }
}
