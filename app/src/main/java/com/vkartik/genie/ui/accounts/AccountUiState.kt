package com.vkartik.genie.ui.accounts

import com.vkartik.genie.domain.AccountCategory
import com.vkartik.genie.domain.model.Account

data class AccountUiState(
    val name: String = "",
    val userName: String = "",
    val password: String = "",
    val iconUrl: String? = "",
    val category: AccountCategory? = AccountCategory.BANK,
    val notes: String? = "",
)

fun AccountUiState.toAccount(): Account = Account(
    name = name,
    userName = userName,
    password = password,
    iconUrl = iconUrl,
    category = category?.ordinal,
    notes = notes
)

fun Account.toAccountUiState(): AccountUiState = AccountUiState(
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