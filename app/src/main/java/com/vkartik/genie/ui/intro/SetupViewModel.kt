package com.vkartik.genie.ui.intro

import android.content.SharedPreferences
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SetupViewModel @Inject constructor(val sharedPreferences: SharedPreferences): ViewModel() {
    private val _uiState = mutableStateOf(SetupUiState())
    val uiState: State<SetupUiState> get() = _uiState

    fun setupAuthentication() {
        with(sharedPreferences.edit()) {
            putString("master_key", _uiState.value.masterKey)
            putString("phone_number", _uiState.value.phoneNumber)
            putBoolean("is_biometric_enabled", _uiState.value.isBiometricEnabled)
            apply()
        }
    }

    fun onMasterKeyChanged(masterKey: String) {
        _uiState.value.copy(masterKey = masterKey)
    }

    fun onPhoneNumberChanged(phoneNumber: String) {
        _uiState.value.copy(phoneNumber = phoneNumber)
    }

    fun enableBiometric(isBiometricEnabled: Boolean) {
        _uiState.value.copy(isBiometricEnabled = isBiometricEnabled)
    }
}