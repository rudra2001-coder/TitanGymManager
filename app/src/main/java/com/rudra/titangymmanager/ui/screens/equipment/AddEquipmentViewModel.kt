package com.rudra.titangymmanager.ui.screens.equipment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rudra.titangymmanager.data.model.Equipment
import com.rudra.titangymmanager.data.repository.EquipmentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEquipmentViewModel @Inject constructor(
    private val equipmentRepository: EquipmentRepository
) : ViewModel() {

    fun addEquipment(equipment: Equipment) {
        viewModelScope.launch {
            equipmentRepository.insertEquipment(equipment)
        }
    }
}
