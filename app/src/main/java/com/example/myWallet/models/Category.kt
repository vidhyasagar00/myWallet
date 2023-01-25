package com.example.myWallet.models

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class Category(
    @PrimaryKey(autoGenerate = true) var id: Int?,
    val title: String,
    val type: String,
    val icon: Int
) {
    companion object {
        const val INCOME = "income"
        const val EXPENSE = "expense"
        const val TRANSFER = "transfer"
    }
}
/*

enum class CategoryEnum{
    INCOME,
    EXPENSE,
    TRANSFER
}*/
