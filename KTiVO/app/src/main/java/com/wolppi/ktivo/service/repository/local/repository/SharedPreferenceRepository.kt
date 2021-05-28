package com.wolppi.ktivo.service.repository.local.repository

import android.content.Context
import android.content.SharedPreferences
import com.wolppi.ktivo.service.constants.KtivoConstants

class SharedPreferenceRepository (context: Context){

    private val mPreferences: SharedPreferences = context.getSharedPreferences(KtivoConstants.SHARED.KTIVO_SHARED, Context.MODE_PRIVATE)

    fun saveShared(key: String, value: String) {
         mPreferences.edit().putString(key, value).apply()
    }

    fun removeShared(key: String) {
        mPreferences.edit().remove(key).apply()
    }

    fun getShared(key: String): String {
        return mPreferences.getString(key, "") ?: ""
    }

    fun allClear() {
        return mPreferences.all.clear()
    }

}
