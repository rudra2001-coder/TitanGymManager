package com.rudra.titangymmanager.ui.screens.packages

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rudra.titangymmanager.data.repository.MembershipPackageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MembershipPackageListViewModel @Inject constructor(
    private val packageRepository: MembershipPackageRepository
) : ViewModel() {

    val packages = packageRepository.getAllPackages()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
}
