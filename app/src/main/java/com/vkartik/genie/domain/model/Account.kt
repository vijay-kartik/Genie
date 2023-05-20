package com.vkartik.genie.domain.model

data class Account(
    val name: String,
    val userName: String,
    val password: String,
    val iconUrl: String?,
    val category: Int?,
    val notes: String?
)