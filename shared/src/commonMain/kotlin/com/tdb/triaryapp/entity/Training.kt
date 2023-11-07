package com.tdb.triaryapp.entity

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class Training(): RealmObject {
    @PrimaryKey
    var id: ObjectId = ObjectId()
    var name: String = ""
    var description: String = ""

    constructor(name: String = "", description: String = "") : this() {
        this.name = name
        this.description = description
    }

    constructor(id: ObjectId, name: String, description: String) : this() {
        this.id = id
        this.name = name
        this.description = description
    }
}