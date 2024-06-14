package com.lutfisobri.soca.data.preference.auth

import android.content.Context
import com.lutfisobri.soca.utils.Cons

class AuthPreference(context: Context) {
    private val sharedPref = context.getSharedPreferences(Cons.AUTH_PREFERENCE_NAME, Context.MODE_PRIVATE)
    private val editor = sharedPref.edit()

    fun saveToken(token: String) {
        editor.putString(Cons.LOGIN_KEY, token)
        editor.apply()
    }

    fun getToken(): String? {
        return sharedPref.getString(Cons.LOGIN_KEY, null)
    }

    fun clearToken() {
        editor.remove(Cons.LOGIN_KEY)
        editor.apply()
    }
}