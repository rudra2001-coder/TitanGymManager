package com.rudra.titangymmanager.ui.screens.member

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rudra.titangymmanager.data.model.Member
import com.rudra.titangymmanager.data.repository.MemberRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class MemberListViewModel @Inject constructor(
    private val memberRepository: MemberRepository
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    val members: StateFlow<List<Member>> = searchQuery
        .flatMapLatest { query ->
            if (query.isBlank()) {
                memberRepository.getAllMembers()
            } else {
                memberRepository.searchMembers(query)
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }

    fun updateMember(member: Member) {
        viewModelScope.launch {
            memberRepository.updateMember(member)
        }
    }

    fun deleteMember(member: Member) {
        viewModelScope.launch {
            memberRepository.deleteMember(member)
        }
    }
}
