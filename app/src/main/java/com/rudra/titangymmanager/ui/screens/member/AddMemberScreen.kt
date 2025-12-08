package com.rudra.titangymmanager.ui.screens.member

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rudra.titangymmanager.R
import com.rudra.titangymmanager.data.model.Member

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMemberScreen(
    viewModel: AddMemberViewModel = hiltViewModel(),
    onMemberAdded: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.add_member)) }
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
                    val newMember = Member(
                        name = name,
                        phoneNumber = phone,
                        gender = gender,
                        age = age.toIntOrNull() ?: 0,
                        joiningDate = System.currentTimeMillis(),
                        membershipType = "monthly", // Dummy data for now
                        membershipStartDate = System.currentTimeMillis(),
                        membershipEndDate = System.currentTimeMillis() + 2592000000, // 30 days
                        feesPaid = 0.0,
                        dueAmount = 500.0, // Dummy data for now
                        paymentMethod = "Cash" // Dummy data for now
                    )
                    viewModel.addMember(newMember)
                    onMemberAdded()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(id = R.string.save_member))
            }
        }
    }
}
