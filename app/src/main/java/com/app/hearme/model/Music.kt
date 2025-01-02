package com.app.hearme.model

import java.util.*

data class Music(
    val musicID: String,
    val musicName: String,
    val totalListeners: Int = 0,
    val image: String,
    val duration: Time,
    val release: Date,
    val category: Category,
    val artist: Artist,
    val isAlbum: Boolean,
    val path: String = "https://cdn.pixabay.com/audio/2023/07/03/audio_80cd47077b.mp3",
    val information: String? = null,
    var isPlaying: Boolean? = false
)
