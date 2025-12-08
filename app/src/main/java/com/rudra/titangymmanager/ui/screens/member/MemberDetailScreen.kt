package com.rudra.titangymmanager.ui.screens.member

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rudra.titangymmanager.R
import com.rudra.titangymmanager.data.model.Member

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemberDetailScreen(
    viewModel: MemberDetailViewModel = hiltViewModel(),
    navController: NavController
) {
    val member by viewModel.member.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(member.name) },
                actions = {
                    IconButton(onClick = { navController.navigate("edit_member/${member.id}") }) {
                        Icon(Icons.Default.Edit, contentDescription = stringResource(id = R.string.edit_member))
                    }
                    IconButton(onClick = {
                        viewModel.deleteMember()
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Default.Delete, contentDescription = stringResource(id = R.string.delete_member))
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Text("Name: ${member.name}")
            Text("Phone: ${member.phoneNumber}")
            Text("Gender: ${member.gender}")
            Text("Age: ${member.age}")
            Text("Joining Date: ${member.joiningDate}")
        }
    }
}
