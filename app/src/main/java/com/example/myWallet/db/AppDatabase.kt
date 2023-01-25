package com.example.myWallet.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myWallet.db.converters.BitmapConverter
import com.example.myWallet.models.Account
import com.example.myWallet.models.Category

@Database(
    entities = [Category::class, Account::class],
    version = 1
)
@TypeConverters(BitmapConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun accountsDao(): AccountsDao
    abstract fun categoryDao(): CategoryDao
}