package com.azartech.tradestore

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!SessionManager.isLoggedIn(this)) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }
        setContentView(R.layout.activity_dashboard)

        val role = SessionManager.role(this)
        val name = SessionManager.username(this)
        findViewById<TextView>(R.id.tvWelcome).text = "Welcome, $name"
        findViewById<TextView>(R.id.tvRole).text = role

        val navStock = findViewById<Button>(R.id.navStockItems)
        val navPurchase = findViewById<Button>(R.id.navPurchase)
        val navMasters = findViewById<Button>(R.id.navMasters)
        val navImport = findViewById<Button>(R.id.navImport)
        val navReports = findViewById<Button>(R.id.navReports)
        val navUsers = findViewById<Button>(R.id.navUsers)
        val btnPos = findViewById<Button>(R.id.btnPOS)

        fun open(destination: String, target: Class<*>) {
            if (SessionManager.canAccess(role, destination)) {
                startActivity(Intent(this, target))
            }
        }

        btnPos.setOnClickListener {
            if (SessionManager.canAccess(role, "pos")) {
                startActivity(Intent(this, StockItemsActivity::class.java).putExtra("mode", "pos"))
            }
        }
        navStock.setOnClickListener { open("stock", StockItemsActivity::class.java) }
        navPurchase.setOnClickListener { open("purchase", PurchaseOrderActivity::class.java) }
        navMasters.setOnClickListener { open("masters", MastersActivity::class.java) }
        navImport.setOnClickListener { open("import", ImportDataActivity::class.java) }
        navReports.setOnClickListener { open("reports", ReportsActivity::class.java) }
        navUsers.setOnClickListener { open("users", UsersActivity::class.java) }
        findViewById<Button>(R.id.navProfile).setOnClickListener { open("profile", ProfileActivity::class.java) }

        // Cashiers only need checkout and their profile.
        val managerAdminViews = listOf(navStock, navPurchase, navMasters, navImport, navReports)
        val adminOnlyViews = listOf(navUsers)
        if (role == SessionManager.CASHIER) managerAdminViews.forEach { it.visibility = View.GONE }
        if (role != SessionManager.ADMIN) adminOnlyViews.forEach { it.visibility = View.GONE }

        // Managers and administrators can access the full POS entry point.
        btnPos.visibility = View.VISIBLE
    }
}
