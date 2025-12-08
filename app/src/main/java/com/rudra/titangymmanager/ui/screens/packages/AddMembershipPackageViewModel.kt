package com.rudra.titangymmanager.ui.screens.packages

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rudra.titangymmanager.data.model.MembershipPackage
import com.rudra.titangymmanager.data.repository.MembershipPackageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddMembershipPackageViewModel @Inject constructor(
    private val packageRepository: MembershipPackageRepository
) : ViewModel() {

    fun addPackage(membershipPackage: MembershipPackage) {
        viewModelScope.launch {
            packageRepository.insertPackage(membershipPackage)
        }
    }
}
