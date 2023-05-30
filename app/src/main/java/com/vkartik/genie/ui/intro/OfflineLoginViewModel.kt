package com.vkartik.genie.ui.intro

import android.content.SharedPreferences
import android.util.Base64
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.vkartik.genie.ui.utils.KeystoreHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import java.security.KeyStore
import javax.inject.Inject

@HiltViewModel
class OfflineLoginViewModel @Inject constructor(private val sharedPreferences: SharedPreferences) :
    ViewModel() {
    var isBiometricEnabled =
        mutableStateOf(sharedPreferences.getBoolean("is_biometric_enabled", false))

    fun isLoginDetailsCorrect(masterKey: String): Boolean {
        val encryptedMasterKeyString = sharedPreferences.getString("master_key", null)
        val ivString = sharedPreferences.getString("iv", null)
        if(encryptedMasterKeyString != null && ivString != null) {
            val encryptedMasterKey = Base64.decode(encryptedMasterKeyString, Base64.DEFAULT)
            val iv = Base64.decode(ivString, Base64.DEFAULT)
            val cipher = KeystoreHelper.getDecryptionCipher(iv)
            val decryptedMasterKey = String(cipher.doFinal(encryptedMasterKey))
            return decryptedMasterKey == masterKey
        }
        return false
    }
}