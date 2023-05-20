package com.vkartik.genie.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "accounts")
data class AccountEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val userName: String,
    val password: String,
    val iconUrl: String?,
    val category: Int?,
    val notes: String?
)