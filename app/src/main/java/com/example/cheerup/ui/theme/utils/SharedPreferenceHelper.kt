package com.example.cheerup.data.utils

import android.content.Context


class SharedPreferenceHelper(private val context: Context) {


    companion object {

        private const val PREF_KEY = "MY_PREF"
    }


    fun saveStringData(key: String, data: String?) {
        val sharedPreferences = context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE)
        sharedPreferences.edit()
            .putString(key, data)
            .apply()
    }

    fun getStringData(key: String): String? {
        val sharedPreferences = context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE)
        return sharedPreferences.getString(key, null)
    }

    fun clearPreferences() {
        val sharedPreferences = context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()
    }
}












