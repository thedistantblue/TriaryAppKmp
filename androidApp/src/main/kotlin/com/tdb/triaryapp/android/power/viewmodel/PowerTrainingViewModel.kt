package com.tdb.triaryapp.android.power.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.tdb.triaryapp.entity.Training
import com.tdb.triaryapp.repository.TrainingRepository
import org.mongodb.kbson.ObjectId

class PowerTrainingViewModel(
    private val repository: TrainingRepository
) : ViewModel() {
    val uiState: MutableState<Training> = mutableStateOf(Training())
    private var currentTraining = uiState.value

    fun updateTrainingName(name: String) {
        currentTraining = Training(name, currentTraining.description)
        uiState.value = currentTraining
    }

    fun updateTrainingDescription(description: String) {
        currentTraining = Training(currentTraining.name, description)
        uiState.value = currentTraining
    }

    fun getTraining(trainingId: ObjectId) {
        currentTraining = repository.findById(trainingId)
        uiState.value = currentTraining
    }

    fun createTraining(training: Training) {
        repository.create(training)
    }

    fun saveTraining(training: Training) {
        repository.save(training)
    }
}