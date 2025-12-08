package com.rudra.titangymmanager.ui.screens.trainer

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rudra.titangymmanager.R
import com.rudra.titangymmanager.data.model.Trainer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTrainerScreen(
    viewModel: AddTrainerViewModel = hiltViewModel(),
    onTrainerAdded: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var salary by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.add_trainer)) }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text(stringResource(id = R.string.trainer_name)) },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = salary,
                onValueChange = { salary = it },
                label = { Text(stringResource(id = R.string.salary)) },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    val newTrainer = Trainer(
                        name = name,
                        salary = salary.toDoubleOrNull() ?: 0.0,
                        joiningDate = System.currentTimeMillis()
                    )
                    viewModel.addTrainer(newTrainer)
                    onTrainerAdded()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(id = R.string.save_trainer))
            }
        }
    }
}
