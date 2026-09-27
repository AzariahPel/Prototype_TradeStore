package com.azartech.tradestore

import android.content.Context

object SessionManager {
    private const val PREFS = "tradestore_session"
    private const val KEY_LOGGED_IN = "logged_in"
    private const val KEY_USERNAME = "username"
    private const val KEY_ROLE = "role"

    const val CASHIER = "Cashier"
    const val MANAGER = "Store Manager"
    const val ADMIN = "System Administrator"

    fun login(context: Context, username: String, role: String) {
        context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
            .edit()
            .putBoolean(KEY_LOGGED_IN, true)
            .putString(KEY_USERNAME, username.ifBlank { "User" })
            .putString(KEY_ROLE, role)
            .apply()
    }

    fun logout(context: Context) {
        context.getSharedPreferences(PREFS, Context.MODE_PRIVATE).edit().clear().apply()
    }

    fun isLoggedIn(context: Context): Boolean =
        context.getSharedPreferences(PREFS, Context.MODE_PRIVATE).getBoolean(KEY_LOGGED_IN, false)

    fun username(context: Context): String =
        context.getSharedPreferences(PREFS, Context.MODE_PRIVATE).getString(KEY_USERNAME, "User") ?: "User"

    fun role(context: Context): String =
        context.getSharedPreferences(PREFS, Context.MODE_PRIVATE).getString(KEY_ROLE, MANAGER) ?: MANAGER

    fun canAccess(role: String, destination: String): Boolean = when (role) {
        CASHIER -> destination in setOf("dashboard", "pos", "profile")
        MANAGER -> destination in setOf("dashboard", "pos", "stock", "purchase", "masters", "import", "reports", "profile")
        ADMIN -> true
        else -> false
    }
}
