package com.tdb.triaryapp

import com.tdb.triaryapp.repository.TrainingRepository
import com.tdb.triaryapp.repository.TrainingRepositoryImpl

class RepositoryFactory {
    companion object {
        fun createTrainingRepository(): TrainingRepository {
            return TrainingRepositoryImpl()
        }
    }
}