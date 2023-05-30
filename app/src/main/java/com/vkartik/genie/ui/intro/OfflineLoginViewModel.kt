package com.vkartik.genie.ui.intro

import android.content.SharedPreferences
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OfflineLoginViewModel @Inject constructor(val sharedPreferences: SharedPreferences) :
    ViewModel() {
    var isBiometricEnabled =
        mutableStateOf(sharedPreferences.getBoolean("is_biometric_enabled", false))

    fun isLoginDetailsCorrect(masterKey: String): Boolean {
        val correctKey = sharedPreferences.getString("master_key", "----")
        return correctKey.equals(masterKey)
    }
}