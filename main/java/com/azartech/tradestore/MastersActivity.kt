package com.azartech.tradestore

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MastersActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!SessionManager.isLoggedIn(this) || !SessionManager.canAccess(SessionManager.role(this), "masters")) {
            startActivity(Intent(this, DashboardActivity::class.java))
            finish()
            return
        }
        setContentView(R.layout.activity_masters)
    }
}
