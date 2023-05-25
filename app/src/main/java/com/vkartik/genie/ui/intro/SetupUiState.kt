package com.vkartik.genie.ui.intro

import androidx.security.crypto.MasterKey

data class SetupUiState(
    val masterKey: String = "",
    val phoneNumber: String = "",
    val isBiometricEnabled: Boolean = false
)