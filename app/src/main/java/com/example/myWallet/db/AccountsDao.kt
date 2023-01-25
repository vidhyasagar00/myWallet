package com.example.myWallet.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myWallet.models.Account

@Dao
interface AccountsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDefaultAccounts(account: List<Account>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewAccount(account: Account)

    @Query("SELECT * FROM my_account")
    suspend fun getAllAccounts(): List<Account>

    @Query("SELECT * FROM my_account WHERE id = :id")
    fun getAccountById(id: Int) : Account
}