package com.example.myWallet.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface BudgetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert()
}