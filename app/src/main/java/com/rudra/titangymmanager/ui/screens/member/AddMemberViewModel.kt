package com.rudra.titangymmanager.ui.screens.member

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rudra.titangymmanager.data.model.Member
import com.rudra.titangymmanager.data.repository.MemberRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddMemberViewModel @Inject constructor(
    private val memberRepository: MemberRepository
) : ViewModel() {

    fun addMember(member: Member) {
        viewModelScope.launch {
            memberRepository.insertMember(member)
        }
    }
}
