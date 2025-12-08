package com.rudra.titangymmanager.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val icon: ImageVector, val title: String) {
    object Dashboard : BottomNavItem("dashboard", Icons.Filled.Dashboard, "Dashboard")
    object Members : BottomNavItem("members", Icons.Filled.People, "Members")
    object Packages : BottomNavItem("packages", Icons.Filled.CardMembership, "Packages")
    object Expiry : BottomNavItem("expiry", Icons.Filled.Notifications, "Expiry")
    object Expenses : BottomNavItem("expenses", Icons.Filled.Money, "Expenses")
    object Equipment : BottomNavItem("equipment", Icons.Filled.FitnessCenter, "Equipment")
    object Trainers : BottomNavItem("trainers", Icons.Filled.Person, "Trainers")
    object Reports : BottomNavItem("reports", Icons.Filled.Assessment, "Reports")
    object Settings : BottomNavItem("settings", Icons.Filled.Settings, "Settings")
}
