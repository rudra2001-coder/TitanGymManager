package com.rudra.titangymmanager.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CardMembership
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.People
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val icon: ImageVector, val title: String) {
    object Dashboard : BottomNavItem("dashboard", Icons.Filled.Dashboard, "Dashboard")
    object Members : BottomNavItem("members", Icons.Filled.People, "Members")
    object Packages : BottomNavItem("packages", Icons.Filled.CardMembership, "Packages")
    object Expiry : BottomNavItem("expiry", Icons.Filled.Notifications, "Expiry")
}
