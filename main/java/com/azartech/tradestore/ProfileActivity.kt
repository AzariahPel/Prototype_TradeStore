package com.azartech.tradestore

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!SessionManager.isLoggedIn(this)) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }
        setContentView(R.layout.activity_profile)

        val name = SessionManager.username(this)
        val role = SessionManager.role(this)
        findViewById<TextView>(R.id.tvProfileInfo).text = "$name\n$role\n$name@tradestore.local"

        findViewById<Button>(R.id.btnLogout).setOnClickListener {
            SessionManager.logout(this)
            startActivity(Intent(this, LoginActivity::class.java))
            finishAffinity()
        }
    }
}
