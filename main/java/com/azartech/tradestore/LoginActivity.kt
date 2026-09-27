package com.azartech.tradestore

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val username = findViewById<EditText>(R.id.etUsername)
        val roleSpinner = findViewById<Spinner>(R.id.spRole)
        roleSpinner.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            listOf(SessionManager.CASHIER, SessionManager.MANAGER, SessionManager.ADMIN)
        )

        findViewById<Button>(R.id.btnLogin).setOnClickListener {
            val name = username.text.toString().trim()
            if (name.isBlank()) {
                username.error = "Enter your username or email"
                username.requestFocus()
                return@setOnClickListener
            }

            val selectedRole = roleSpinner.selectedItem.toString()
            SessionManager.login(this, name, selectedRole)
            Toast.makeText(this, "Logged in as $selectedRole", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, DashboardActivity::class.java))
            finish()
        }

        findViewById<TextView>(R.id.tvRegister).setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}
