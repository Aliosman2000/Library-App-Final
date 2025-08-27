package com.egitim.kutuphee_yazilimi

import android.content.Context
import android.content.SharedPreferences

class KullaniciYonetımı(context: Context) {

    private val PREF_NAME = "user_session_prefs"


    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)


    private val editor: SharedPreferences.Editor = sharedPreferences.edit()


    private val KEY_IS_LOGGED_IN = "is_logged_in"


    private val KEY_USERNAME = "username"


    fun saveUserSession(isLoggedIn: Boolean, username: String) {
        // Editöre verileri ekle
        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn)
        editor.putString(KEY_USERNAME, username)

        editor.commit()
    }


    fun getUserSession(): Pair<Boolean, String?> {

        val isLoggedIn = sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false)
        val username = sharedPreferences.getString(KEY_USERNAME, null)
        return Pair(isLoggedIn, username)
    }


    fun clearUserSession() {
        editor.clear() // Tüm verileri sil
        editor.commit()
    }
}