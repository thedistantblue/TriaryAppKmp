package com.tdb.triaryapp.repository

import com.tdb.triaryapp.entity.Training
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import org.mongodb.kbson.ObjectId

class TrainingRepositoryImpl : TrainingRepository {
    private val config = RealmConfiguration.create(schema = setOf(Training::class))
    private val realm: Realm = Realm.open(config)

    override fun create(training: Training) {
        realm.writeBlocking {
            copyToRealm(training)
        }
    }

    override fun save(training: Training) {
        realm.writeBlocking {
            copyToRealm(training)
        }
    }

    override fun delete(training: Training) {
        realm.writeBlocking { delete(training) }
    }

    override fun findById(trainingId: ObjectId): Training {
        return realm.query(Training::class, "_id == $trainingId").find().first()
    }

    override fun findAllById(trainingIds: Collection<ObjectId>): Collection<Training> {
        TODO("Not yet implemented")
    }

    override fun findAll(): Collection<Training> {
        return realm.query(Training::class).find()
    }
}