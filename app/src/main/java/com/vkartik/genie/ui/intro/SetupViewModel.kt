package com.vkartik.genie.ui.intro

import android.content.SharedPreferences
import android.util.Base64
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.vkartik.genie.ui.utils.KeystoreHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SetupViewModel @Inject constructor(val sharedPreferences: SharedPreferences): ViewModel() {
    private val _uiState = mutableStateOf(SetupUiState())
    val uiState: State<SetupUiState> get() = _uiState

    fun setupAuthentication() {
        val cipher = KeystoreHelper.getEncryptionCipher()
        val encryptedMasterKey = cipher.doFinal(_uiState.value.masterKey.toByteArray())
        val ivString = Base64.encodeToString(cipher.iv, Base64.DEFAULT)
        val ek = Base64.encodeToString(encryptedMasterKey, Base64.DEFAULT)
        with(sharedPreferences.edit()) {
            putString("master_key", ek)
            putString("iv", ivString)
            putString("phone_number", _uiState.value.phoneNumber)
            putBoolean("is_biometric_enabled", _uiState.value.isBiometricEnabled)
            apply()
        }
    }

    fun onMasterKeyChanged(masterKey: String) {
        Log.e("kartik", "bf change $masterKey")
        _uiState.value = _uiState.value.copy(masterKey = masterKey)
        Log.e("kartik", "mk ${_uiState.value.masterKey}")
    }

    fun onPhoneNumberChanged(phoneNumber: String) {
        Log.e("kartik", "bf change $phoneNumber")
        _uiState.value = _uiState.value.copy(phoneNumber = phoneNumber)
        Log.e("kartik", "pn ${_uiState.value.phoneNumber}")
    }

    fun enableBiometric(isBiometricEnabled: Boolean) {
        _uiState.value = _uiState.value.copy(isBiometricEnabled = isBiometricEnabled)
    }

    fun saveUserSetupAsComplete() {
        with(sharedPreferences.edit()) {
            putBoolean("setup_complete", true)
            apply()
        }
    }
}