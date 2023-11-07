package com.tdb.triaryapp.repository

import com.tdb.triaryapp.entity.Training
import org.mongodb.kbson.ObjectId

interface TrainingRepository {
    fun create(training: Training)
    fun save(training: Training)
    fun delete(training: Training)
    fun findById(trainingId: ObjectId): Training
    fun findAllById(trainingIds: Collection<ObjectId>): Collection<Training>
    fun findAll(): Collection<Training>
}