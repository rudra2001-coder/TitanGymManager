package com.rudra.titangymmanager.ui.screens.packages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.rudra.titangymmanager.R
import com.rudra.titangymmanager.data.model.MembershipPackage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MembershipPackageListScreen(
    viewModel: MembershipPackageListViewModel = hiltViewModel(),
    onAddPackage: () -> Unit,
    onPackageClick: (MembershipPackage) -> Unit
) {
    val packages by viewModel.packages.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.membership_packages)) }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddPackage) {
                Icon(Icons.Default.Add, contentDescription = stringResource(id = R.string.add_package))
            }
        }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(packages) { pkg ->
                MembershipPackageListItem(packageItem = pkg, onClick = { onPackageClick(pkg) })
            }
        }
    }
}

@Composable
fun MembershipPackageListItem(
    packageItem: MembershipPackage,
    onClick: () -> Unit
) {
    ListItem(
        headlineContent = { Text(packageItem.name) },
        supportingContent = { Text("${packageItem.price} for ${packageItem.duration} days") },
        modifier = Modifier.clickable(onClick = onClick)
    )
}
