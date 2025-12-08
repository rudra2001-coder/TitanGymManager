package com.rudra.titangymmanager.ui.screens.equipment

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
import com.rudra.titangymmanager.data.model.Equipment

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EquipmentListScreen(
    viewModel: EquipmentListViewModel = hiltViewModel(),
    onAddEquipment: () -> Unit,
    onEquipmentClick: (Equipment) -> Unit
) {
    val equipment by viewModel.equipment.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.equipment)) }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddEquipment) {
                Icon(Icons.Default.Add, contentDescription = stringResource(id = R.string.add_equipment))
            }
        }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(equipment) { item ->
                EquipmentListItem(equipment = item, onClick = { onEquipmentClick(item) })
            }
        }
    }
}

@Composable
fun EquipmentListItem(
    equipment: Equipment,
    onClick: () -> Unit
) {
    ListItem(
        headlineContent = { Text(equipment.name) },
        supportingContent = { Text("Quantity: ${equipment.quantity} - Condition: ${equipment.condition}") },
        modifier = Modifier.clickable(onClick = onClick)
    )
}
