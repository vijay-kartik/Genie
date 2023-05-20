package com.vkartik.genie.domain.model

data class User(
    val id: String = "",
    val isAnonymous: Boolean = true,
    val isRealUser: Boolean = false
)