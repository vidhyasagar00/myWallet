package com.example.myWallet.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_account")
data class Account(
    @PrimaryKey(autoGenerate = true) var id: Int?,
    val title: String,
    val initialAmount: String,
    val icon: Int
)