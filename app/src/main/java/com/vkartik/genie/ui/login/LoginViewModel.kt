package com.vkartik.genie.ui.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.makeitso.common.ext.isValidEmail
import com.vkartik.genie.GenieViewModel
import com.vkartik.genie.R
import com.vkartik.genie.domain.model.service.AccountService
import com.vkartik.genie.domain.model.service.LogService
import com.vkartik.genie.ui.snackbar.SnackbarManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val accountService: AccountService,
    logService: LogService
) :
    GenieViewModel(logService) {
    var uiState = mutableStateOf(LoginUiState())
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

    fun onSignInClick(openAndPopUp: (String, String) -> Unit) {
        if (!email.isValidEmail()) {
            SnackbarManager.showMessage(R.string.email_error)
            return
        }

        if (password.isBlank()) {
            SnackbarManager.showMessage(R.string.empty_password_error)
            return
        }

//        launchCatching {
//            accountService.authenticate(email, password)
//            TODO()
//            openAndPopUp(SETTINGS_SCREEN, LOGIN_SCREEN)
//        }
    }

    fun onForgotPasswordClick() {
        if (!email.isValidEmail()) {
            SnackbarManager.showMessage(R.string.email_error)
            return
        }

        launchCatching {
            accountService.sendRecoveryEmail(email)
            SnackbarManager.showMessage(R.string.recovery_email_sent)
        }
    }

}