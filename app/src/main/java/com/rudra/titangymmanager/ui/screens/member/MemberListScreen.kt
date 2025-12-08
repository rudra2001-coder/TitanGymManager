package com.rudra.titangymmanager.ui.screens.member

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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rudra.titangymmanager.R
import com.rudra.titangymmanager.data.model.Member

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemberListScreen(
    viewModel: MemberListViewModel = hiltViewModel(),
    onAddMember: () -> Unit,
    onMemberClick: (Member) -> Unit
) {
    val members by viewModel.members.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.app_name)) }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddMember) {
                Icon(Icons.Default.Add, contentDescription = stringResource(id = R.string.add_member))
            }
        }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(members) { member ->
                MemberListItem(member = member, onClick = { onMemberClick(member) })
            }
        }
    }
}

@Composable
fun MemberListItem(
    member: Member,
    onClick: () -> Unit
) {
    ListItem(
        headlineContent = { Text(member.name) },
        supportingContent = { Text(member.phoneNumber) },
        modifier = Modifier.clickable(onClick = onClick)
    )
}
