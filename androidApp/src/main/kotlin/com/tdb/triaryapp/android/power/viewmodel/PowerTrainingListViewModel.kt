package com.tdb.triaryapp.android.power.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.tdb.triaryapp.entity.Training
import com.tdb.triaryapp.repository.TrainingRepository

class PowerTrainingListViewModel(
    private val repository: TrainingRepository
) : ViewModel() {
    val uiState: MutableState<List<Training>> = mutableStateOf(listOf())

    fun getTrainings() {
        uiState.value = repository.findAll().toList()
    }

    fun deleteTraining(training: Training) {
        repository.delete(training)
        getTrainings()
    }
}