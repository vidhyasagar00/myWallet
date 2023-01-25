package com.example.myWallet.di

import android.content.Context
import androidx.room.Room
import com.example.myWallet.db.AccountsDao
import com.example.myWallet.db.AppDatabase
import com.example.myWallet.db.CategoryDao
import com.example.myWallet.db.defaultDatas.AccountCallBack
import com.example.myWallet.db.defaultDatas.CategoryCallBack
import com.example.myWallet.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomDBModel {

    @Singleton
    @Provides
    fun roomInstance(
        @ApplicationContext context: Context,
        categoryProvider: Provider<CategoryDao>,
        accountProvider: Provider<AccountsDao>
    ): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, Constants.APP_DB)
            .fallbackToDestructiveMigration()
            .addCallback(CategoryCallBack(categoryProvider))
            .addCallback(AccountCallBack(accountProvider))
            .build()

    @Singleton
    @Provides
    fun categoryDao(database: AppDatabase): CategoryDao = database.categoryDao()

    @Singleton
    @Provides
    fun accountDao(database: AppDatabase): AccountsDao = database.accountsDao()
}