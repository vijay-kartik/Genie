package com.vkartik.genie.ui.intro

data class SetupUiState(
    val masterKey: String = "",
    val phoneNumber: String = "",
    val isBiometricEnabled: Boolean = false
)