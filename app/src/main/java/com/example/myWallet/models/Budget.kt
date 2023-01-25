package com.example.myWallet.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalTime
import java.util.Date

@Entity(tableName = "budget")
data class Budget(
    @PrimaryKey(autoGenerate = true) var id: Int,
    var category: Int,
    var account: Int,
    var date: LocalDate,
    var time: LocalTime,
    var amount: String,
    var notes: String,
)
