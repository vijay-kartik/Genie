package com.vkartik.genie.domain.model

import com.google.firebase.firestore.DocumentId

data class Account(
    val id: Int = 0,
    val name: String,
    val userName: String,
    val password: String,
    val iconUrl: String?,
    val category: Int?,
    val notes: String?
)