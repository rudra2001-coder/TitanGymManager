package com.rudra.titangymmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*
import com.rudra.titangymmanager.ui.navigation.BottomNavItem
import com.rudra.titangymmanager.ui.screens.dashboard.DashboardScreen
import com.rudra.titangymmanager.ui.screens.expiry.ExpiryScreen
import com.rudra.titangymmanager.ui.screens.member.AddMemberScreen
import com.rudra.titangymmanager.ui.screens.member.MemberListScreen
import com.rudra.titangymmanager.ui.screens.packages.AddPackageScreen
import com.rudra.titangymmanager.ui.screens.packages.MembershipPackageListScreen
import com.rudra.titangymmanager.ui.theme.TitanGymManagerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TitanGymManagerTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = { BottomNavigationBar(navController = navController) }
                ) { paddingValues ->
                    NavHost(
                        navController = navController,
                        startDestination = "main",
                        modifier = Modifier.padding(paddingValues)
                    ) {
                        navigation(startDestination = BottomNavItem.Dashboard.route, route = "main") {
                            composable(BottomNavItem.Dashboard.route) {
                                DashboardScreen()
                            }
                            composable(BottomNavItem.Members.route) {
                                MemberListScreen(
                                    onAddMember = { navController.navigate("add_member") },
                                    onMemberClick = { /* TODO: Navigate to member details */ }
                                )
                            }
                            composable(BottomNavItem.Packages.route) {
                                MembershipPackageListScreen(
                                    onAddPackage = { navController.navigate("add_package") },
                                    onPackageClick = { /* TODO: Navigate to package details */ }
                                )
                            }
                            composable(BottomNavItem.Expiry.route) {
                                ExpiryScreen()
                            }
                        }
                        composable("add_member") {
                            AddMemberScreen(
                                onMemberAdded = { navController.popBackStack() }
                            )
                        }
                        composable("add_package") {
                            AddPackageScreen(
                                onPackageAdded = { navController.popBackStack() }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val items = listOf(
        BottomNavItem.Dashboard,
        BottomNavItem.Members,
        BottomNavItem.Packages,
        BottomNavItem.Expiry
    )

    NavigationBar {
        items.forEach { screen ->
            NavigationBarItem(
                icon = { Icon(screen.icon, contentDescription = null) },
                label = { Text(screen.title) },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}
