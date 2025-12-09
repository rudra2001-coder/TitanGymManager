package com.rudra.titangymmanager.ui.screens.settings

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rudra.titangymmanager.data.repository.BackupRepository
import com.rudra.titangymmanager.data.repository.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository,
    private val backupRepository: BackupRepository
) : ViewModel() {

    val isDarkTheme = settingsRepository.isDarkTheme
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = false
        )

    fun setTheme(isDarkTheme: Boolean) {
        viewModelScope.launch {
            settingsRepository.setTheme(isDarkTheme)
        }
    }

    fun backupDatabase(uri: Uri) {
        viewModelScope.launch {
            backupRepository.backupDatabase(uri)
        }
    }

    fun restoreDatabase(uri: Uri) {
        viewModelScope.launch {
            backupRepository.restoreDatabase(uri)
        }
    }
}
