package com.vkartik.genie.ui.accounts

import com.vkartik.genie.data.AccountEntity
import com.vkartik.genie.domain.AccountCategory

data class AccountUiState(
    val name: String = "",
    val userName: String = "",
    val password: String = "",
    val iconUrl: String? = "",
    val category: AccountCategory? = AccountCategory.BANK,
    val notes: String? = "",
)

fun AccountUiState.toAccount(): AccountEntity = AccountEntity(
    name = name,
    userName = userName,
    password = password,
    iconUrl = iconUrl,
    category = category?.ordinal,
    notes = notes
)

fun AccountEntity.toAccountUiState(): AccountUiState = AccountUiState(
    name,
    userName,
    password,
    iconUrl,
    category = category?.let { AccountCategory.values()[it] },
    notes
)

fun AccountUiState.isValid(): Boolean {
    return name.isNotBlank() && userName.isNotBlank() && password.isNotBlank()
}