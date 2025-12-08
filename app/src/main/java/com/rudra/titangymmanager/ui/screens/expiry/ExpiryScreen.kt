package com.rudra.titangymmanager.ui.screens.expiry

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.rudra.titangymmanager.R
import com.rudra.titangymmanager.data.model.Member
import com.rudra.titangymmanager.ui.screens.member.MemberListItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpiryScreen(
    viewModel: ExpiryViewModel = hiltViewModel()
) {
    val expiringToday by viewModel.expiringToday.collectAsState()
    val expiringIn3Days by viewModel.expiringIn3Days.collectAsState()
    val expiringIn7Days by viewModel.expiringIn7Days.collectAsState()
    val expiringThisMonth by viewModel.expiringThisMonth.collectAsState()

    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf(
        stringResource(id = R.string.today),
        stringResource(id = R.string.in_3_days),
        stringResource(id = R.string.in_7_days),
        stringResource(id = R.string.this_month)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.expiry_alerts)) }
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            TabRow(selectedTabIndex = selectedTabIndex) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = { Text(title) }
                    )
                }
            }
            when (selectedTabIndex) {
                0 -> ExpiryList(members = expiringToday)
                1 -> ExpiryList(members = expiringIn3Days)
                2 -> ExpiryList(members = expiringIn7Days)
                3 -> ExpiryList(members = expiringThisMonth)
            }
        }
    }
}

@Composable
fun ExpiryList(members: List<Member>) {
    LazyColumn {
        items(members) { member ->
            MemberListItem(member = member, onClick = { /* TODO: Implement phone call */ })
        }
    }
}
