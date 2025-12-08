package com.rudra.titangymmanager.ui.screens.reports

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rudra.titangymmanager.R
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportsScreen(
    viewModel: ReportsViewModel = hiltViewModel()
) {
    val income by viewModel.income.collectAsState()
    val expenses by viewModel.expenses.collectAsState()
    var profitLoss by remember { mutableStateOf(0.0) }

    LaunchedEffect(viewModel.profitLoss) {
        viewModel.profitLoss.collectLatest {
            profitLoss = it
        }
    }

    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf(
        stringResource(id = R.string.daily),
        stringResource(id = R.string.weekly),
        stringResource(id = R.string.monthly),
        stringResource(id = R.string.yearly)
    )

    LaunchedEffect(selectedTabIndex) {
        when (selectedTabIndex) {
            0 -> viewModel.loadDailyReport()
            1 -> viewModel.loadWeeklyReport()
            2 -> viewModel.loadMonthlyReport()
            3 -> viewModel.loadYearlyReport()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.reports)) }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            TabRow(selectedTabIndex = selectedTabIndex) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = { Text(title) }
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            ReportCard(title = stringResource(id = R.string.income), value = income.toString())
            Spacer(modifier = Modifier.height(8.dp))
            ReportCard(title = stringResource(id = R.string.expenses), value = expenses.toString())
            Spacer(modifier = Modifier.height(8.dp))
            ReportCard(title = stringResource(id = R.string.profit_loss), value = profitLoss.toString())
        }
    }
}

@Composable
fun ReportCard(title: String, value: String) {
    Card {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title)
            Text(text = value)
        }
    }
}
