package com.tdb.triaryapp

import com.tdb.triaryapp.entity.Training
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.query.RealmResults

class Greeting {
    private val platform: Platform = getPlatform()

    fun greet(): String {
        val config = RealmConfiguration.create(schema = setOf(Training::class))
        val realm: Realm = Realm.open(config)

        realm.writeBlocking {
            copyToRealm(Training(name = "name"))
        }
        val items: RealmResults<Training> = realm.query(Training::class).find()
        val trainingNames = items.map { it.name }
        val trainingIds = items.map { it.id }
        return "Hello, ${platform.name}! Training names: $trainingNames. And ids: $trainingIds"
    }
}