package com.rudra.titangymmanager.ui.screens.member

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rudra.titangymmanager.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditMemberScreen(
    navController: NavController,
    viewModel: MemberDetailViewModel = hiltViewModel()
) {
    val member by viewModel.member.collectAsState()

    var name by remember { mutableStateOf(member.name) }
    var phone by remember { mutableStateOf(member.phoneNumber) }
    var gender by remember { mutableStateOf(member.gender) }
    var age by remember { mutableStateOf(member.age.toString()) }

    LaunchedEffect(member) {
        if (member.id != -1L) { // Check if member is loaded
            name = member.name
            phone = member.phoneNumber
            gender = member.gender
            age = member.age.toString()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.edit_member)) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = stringResource(id = R.string.back))
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text(stringResource(id = R.string.member_name)) },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text(stringResource(id = R.string.phone_number)) },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = gender,
                onValueChange = { gender = it },
                label = { Text(stringResource(id = R.string.gender)) },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = age,
                onValueChange = { age = it },
                label = { Text(stringResource(id = R.string.age)) },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    val updatedMember = member.copy(
                        name = name,
                        phoneNumber = phone,
                        gender = gender,
                        age = age.toIntOrNull() ?: member.age
                    )
                    viewModel.updateMember(updatedMember)
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(id = R.string.save_member))
            }
        }
    }
}
