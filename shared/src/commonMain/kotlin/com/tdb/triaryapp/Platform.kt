package com.tdb.triaryapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform