package com.tdb.triaryapp.repository

import com.tdb.triaryapp.entity.Training
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.query.RealmQuery
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
            copyToRealm(training, UpdatePolicy.ALL)
        }
    }

    override fun delete(training: Training) {
        realm.writeBlocking { delete(training) }
    }

    override fun findById(trainingId: ObjectId): Training {
        return realm.query(Training::class, "_id == $0", trainingId).find().first()
    }

    override fun findAllById(trainingIds: Collection<ObjectId>): Collection<Training> {
        return realm.query(Training::class, "_id IN $0", trainingIds).find()
    }

    override fun findAll(): Collection<Training> {
        return realm.query(Training::class).find()
    }
}