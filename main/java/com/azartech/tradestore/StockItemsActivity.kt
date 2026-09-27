package com.azartech.tradestore

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class StockItemsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mode = intent.getStringExtra("mode") ?: "stock"
        val role = SessionManager.role(this)
        val allowed = if (mode == "pos") {
            SessionManager.canAccess(role, "pos")
        } else {
            SessionManager.canAccess(role, "stock")
        }

        if (!SessionManager.isLoggedIn(this) || !allowed) {
            startActivity(Intent(this, DashboardActivity::class.java))
            finish()
            return
        }
        setContentView(R.layout.activity_stock_items)
        if (mode == "pos") {
            val titleId = resources.getIdentifier("tvTitle", "id", packageName)
            if (titleId != 0) findViewById<android.widget.TextView>(titleId).text = "Sales / Billing (POS)"
            findViewById<android.widget.Button>(R.id.btnAdd).visibility = android.view.View.GONE
            findViewById<android.widget.Button>(R.id.btnUpdate).visibility = android.view.View.GONE
            findViewById<android.widget.Button>(R.id.btnDelete).visibility = android.view.View.GONE
        }
    }
}
