package com.azartech.tradestore

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        findViewById<Button>(R.id.btnRegister).setOnClickListener {
            val nameField = resources.getIdentifier("etName", "id", packageName)
            val emailField = resources.getIdentifier("etEmail", "id", packageName)
            val name = if (nameField != 0) findViewById<EditText>(nameField).text.toString().trim() else "New User"
            val email = if (emailField != 0) findViewById<EditText>(emailField).text.toString().trim() else ""
            SessionManager.login(this, name.ifBlank { email.ifBlank { "New User" } }, SessionManager.MANAGER)
            startActivity(Intent(this, DashboardActivity::class.java))
            finish()
        }
    }
}
