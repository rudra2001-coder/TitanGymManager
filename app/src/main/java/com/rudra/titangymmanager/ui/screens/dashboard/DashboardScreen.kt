package com.rudra.titangymmanager.ui.screens.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rudra.titangymmanager.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel = hiltViewModel()
) {
    val totalMembers by viewModel.totalMembers.collectAsState()
    val activeMembers by viewModel.activeMembers.collectAsState()
    val expiredMembers by viewModel.expiredMembers.collectAsState()
    val dueMembers by viewModel.dueMembers.collectAsState()
    val monthlyNewMembers by viewModel.monthlyNewMembers.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.dashboard)) }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            DashboardCard(title = stringResource(id = R.string.total_members), value = totalMembers.toString())
            Spacer(modifier = Modifier.height(8.dp))
            DashboardCard(title = stringResource(id = R.string.active_members), value = activeMembers.toString())
            Spacer(modifier = Modifier.height(8.dp))
            DashboardCard(title = stringResource(id = R.string.expired_members), value = expiredMembers.toString())
            Spacer(modifier = Modifier.height(8.dp))
            DashboardCard(title = stringResource(id = R.string.due_members), value = dueMembers.toString())
            Spacer(modifier = Modifier.height(16.dp))
            if (activeMembers > 0 || expiredMembers > 0) {
                ActiveExpiredPieChart(active = activeMembers, expired = expiredMembers)
            }
            Spacer(modifier = Modifier.height(16.dp))
            if (monthlyNewMembers.isNotEmpty()) {
                MonthlyNewMembersBarChart(monthlyNewMembers = monthlyNewMembers)
            }
        }
    }
}

@Composable
fun DashboardCard(title: String, value: String) {
    Card {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title)
            Text(text = value)
        }
    }
}
