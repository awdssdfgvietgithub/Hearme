package com.app.hearme.model

data class Artist(
    val artistId: String,
    val artistName: String,
    val image: String? = null,
    var totalNumberOfListeners: Int = 0,
    val isSinger: Boolean = false,
    val information: String? = null,
)
