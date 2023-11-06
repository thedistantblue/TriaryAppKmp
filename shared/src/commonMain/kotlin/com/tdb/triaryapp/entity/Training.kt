package com.tdb.triaryapp.entity

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class Training(): RealmObject {
    @PrimaryKey
    var id: ObjectId = ObjectId();
    var name: String = ""

    constructor(name: String = "") : this() {
        this.name = name
    }
}