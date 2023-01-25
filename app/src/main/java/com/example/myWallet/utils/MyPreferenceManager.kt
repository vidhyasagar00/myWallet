package com.example.myWallet.utils

import android.content.SharedPreferences
import javax.inject.Inject

class MyPreferenceManager @Inject constructor(private val sharedPref : SharedPreferences) {

    private val editor: SharedPreferences.Editor = sharedPref.edit()

    fun set(key: String, value: String) {
        editor.putString(key, value)
            .apply()
    }

    fun set(key: String, value: Int) {
        editor.putInt(key, value)
            .apply()
    }

    fun set(key: String, value: Boolean) {
        editor.putBoolean(key, value)
            .apply()
    }

    fun getInt(key: String) = sharedPref.getInt(key, 0)

    fun getString(key: String) = sharedPref.getString(key, null)

    fun getBoolean(key: String) = sharedPref.getBoolean(key, false)

    fun clear() {
        editor.clear()
            .apply()
    }
}