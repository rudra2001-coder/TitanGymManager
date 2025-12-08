package com.rudra.titangymmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*
import com.rudra.titangymmanager.ui.navigation.BottomNavItem
import com.rudra.titangymmanager.ui.screens.dashboard.DashboardScreen
import com.rudra.titangymmanager.ui.screens.equipment.AddEquipmentScreen
import com.rudra.titangymmanager.ui.screens.equipment.EquipmentDetailScreen
import com.rudra.titangymmanager.ui.screens.equipment.EquipmentListScreen
import com.rudra.titangymmanager.ui.screens.expense.AddExpenseScreen
import com.rudra.titangymmanager.ui.screens.expense.ExpenseDetailScreen
import com.rudra.titangymmanager.ui.screens.expense.ExpenseListScreen
import com.rudra.titangymmanager.ui.screens.expiry.ExpiryScreen
import com.rudra.titangymmanager.ui.screens.member.AddMemberScreen
import com.rudra.titangymmanager.ui.screens.member.EditMemberScreen
import com.rudra.titangymmanager.ui.screens.member.MemberDetailScreen
import com.rudra.titangymmanager.ui.screens.member.MemberListScreen
import com.rudra.titangymmanager.ui.screens.packages.AddPackageScreen
import com.rudra.titangymmanager.ui.screens.packages.MembershipPackageListScreen
import com.rudra.titangymmanager.ui.screens.packages.PackageDetailScreen
import com.rudra.titangymmanager.ui.screens.reports.ReportsScreen
import com.rudra.titangymmanager.ui.screens.settings.SettingsScreen
import com.rudra.titangymmanager.ui.screens.settings.SettingsViewModel
import com.rudra.titangymmanager.ui.screens.trainer.AddTrainerScreen
import com.rudra.titangymmanager.ui.screens.trainer.TrainerDetailScreen
import com.rudra.titangymmanager.ui.screens.trainer.TrainerListScreen
import com.rudra.titangymmanager.ui.theme.TitanGymManagerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val settingsViewModel: SettingsViewModel = hiltViewModel()
            val isDarkTheme by settingsViewModel.isDarkTheme.collectAsState()
            TitanGymManagerTheme(darkTheme = isDarkTheme) {
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
                                    onMemberClick = { member -> navController.navigate("member_detail/${member.id}") }
                                )
                            }
                            composable(BottomNavItem.Packages.route) {
                                MembershipPackageListScreen(
                                    onAddPackage = { navController.navigate("add_package") },
                                    onPackageClick = { pkg -> navController.navigate("package_detail/${pkg.id}") }
                                )
                            }
                            composable(BottomNavItem.Expiry.route) {
                                ExpiryScreen()
                            }
                            composable(BottomNavItem.Expenses.route) {
                                ExpenseListScreen(
                                    onAddExpense = { navController.navigate("add_expense") },
                                    onExpenseClick = { expense -> navController.navigate("expense_detail/${expense.id}") }
                                )
                            }
                            composable(BottomNavItem.Equipment.route) {
                                EquipmentListScreen(
                                    onAddEquipment = { navController.navigate("add_equipment") },
                                    onEquipmentClick = { equipment -> navController.navigate("equipment_detail/${equipment.id}") }
                                )
                            }
                            composable(BottomNavItem.Trainers.route) {
                                TrainerListScreen(
                                    onAddTrainer = { navController.navigate("add_trainer") },
                                    onTrainerClick = { trainer -> navController.navigate("trainer_detail/${trainer.id}") }
                                )
                            }
                            composable(BottomNavItem.Reports.route) {
                                ReportsScreen()
                            }
                            composable(BottomNavItem.Settings.route) {
                                SettingsScreen()
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
                        composable("add_expense") {
                            AddExpenseScreen(
                                onExpenseAdded = { navController.popBackStack() }
                            )
                        }
                        composable("add_equipment") {
                            AddEquipmentScreen(
                                onEquipmentAdded = { navController.popBackStack() }
                            )
                        }
                        composable("add_trainer") {
                            AddTrainerScreen(
                                onTrainerAdded = { navController.popBackStack() }
                            )
                        }
                        composable("member_detail/{memberId}") { 
                            MemberDetailScreen(navController = navController)
                        }
                        composable("edit_member/{memberId}") { 
                            EditMemberScreen(navController = navController)
                        }
                        composable("package_detail/{packageId}") { backStackEntry ->
                            val packageId = backStackEntry.arguments?.getString("packageId")?.toLongOrNull()
                            if (packageId != null) {
                                PackageDetailScreen(packageId = packageId)
                            }
                        }
                        composable("expense_detail/{expenseId}") { backStackEntry ->
                            val expenseId = backStackEntry.arguments?.getString("expenseId")?.toLongOrNull()
                            if (expenseId != null) {
                                ExpenseDetailScreen(expenseId = expenseId)
                            }
                        }
                        composable("equipment_detail/{equipmentId}") { backStackEntry ->
                            val equipmentId = backStackEntry.arguments?.getString("equipmentId")?.toLongOrNull()
                            if (equipmentId != null) {
                                EquipmentDetailScreen(equipmentId = equipmentId)
                            }
                        }
                        composable("trainer_detail/{trainerId}") { backStackEntry ->
                            val trainerId = backStackEntry.arguments?.getString("trainerId")?.toLongOrNull()
                            if (trainerId != null) {
                                TrainerDetailScreen(trainerId = trainerId)
                            }
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
        BottomNavItem.Expiry,
        BottomNavItem.Expenses,
        BottomNavItem.Equipment,
        BottomNavItem.Trainers,
        BottomNavItem.Reports,
        BottomNavItem.Settings
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
