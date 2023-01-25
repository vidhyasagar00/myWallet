package com.example.myWallet.di

import android.content.Context
import android.content.SharedPreferences
import com.example.myWallet.utils.Constants
import com.example.myWallet.utils.MyPreferenceManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PreferenceModule {

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(
            Constants.PREFERENCE_NAME, Context.MODE_PRIVATE
        )

    @Singleton
    @Provides
    fun provideSessionManager(preferences: SharedPreferences): MyPreferenceManager =
        MyPreferenceManager(preferences)
}