[README.md](https://github.com/user-attachments/files/32694270/README.md)
# Prototype_TradeStore# TradeStore XML
A small retail/trade-store Android XML prototype based directly on the supplied requirements.
Includes product management, inventory, POS entry point, role login, reports, purchase orders and the required navigation hierarchy.
Build: compileSdk/targetSdk 34, minSdk 24, Java/JVM 11, Kotlin 2.0.21, AGP 8.7.3.
Real barcode scanning, database persistence, payment processing, encryption, offline synchronization and server-side role authorization are integration work for the next phase.

## Role-based login and permissions

The XML version now stores the selected login role in a local session and applies role-based navigation:

- **Cashier:** Dashboard, Sales/Billing (POS), and My Profile.
- **Store Manager:** Dashboard, Sales/Billing (POS), Stock Items, Purchase Order, Masters, Import Data, Reports, and My Profile.
- **System Administrator:** Full access, including Manage Users.

The selected username and role are shown on the Dashboard and My Profile. Logging out clears the local session. Protected activities also check the current role before opening.

This is UI/session-level role control for the prototype. Production authentication should be connected to a secure backend/database with server-side authorization.

A simple prototype TradeStore Application created mostly with Kotlin
