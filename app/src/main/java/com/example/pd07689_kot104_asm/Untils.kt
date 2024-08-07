package com.example.pd07689_kot104_asm

import android.content.Context
import android.content.SharedPreferences

fun getUserIdFromPreferences(context: Context): String? {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("INFO", Context.MODE_PRIVATE)
    return sharedPreferences.getString("userId", null)
}