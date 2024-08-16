package com.example.myapplication

import android.content.Context

class PrefsManager(private val context: Context) {
    private val prefs = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    fun getCash(): Int {
        return prefs.getInt("coins", 0)
    }

    fun setCash(count: Int) {
        return prefs.edit().putInt("coins", count).apply()
    }
}