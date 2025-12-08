package com.rudra.titangymmanager.ui.screens.trainer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rudra.titangymmanager.data.model.Trainer
import com.rudra.titangymmanager.data.repository.TrainerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTrainerViewModel @Inject constructor(
    private val trainerRepository: TrainerRepository
) : ViewModel() {

    fun addTrainer(trainer: Trainer) {
        viewModelScope.launch {
            trainerRepository.insertTrainer(trainer)
        }
    }
}
