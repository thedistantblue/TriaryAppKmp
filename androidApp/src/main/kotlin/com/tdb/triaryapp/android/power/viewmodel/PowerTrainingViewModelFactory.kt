package com.tdb.triaryapp.android.power.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.tdb.triaryapp.repository.TrainingRepository

class PowerTrainingViewModelFactory {
    companion object {
        fun getFactory(repository: TrainingRepository): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                    return PowerTrainingViewModel(repository) as T
                }
            }
        }
    }
}