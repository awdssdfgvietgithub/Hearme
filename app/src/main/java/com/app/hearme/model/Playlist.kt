package com.app.hearme.model

class Playlist(
    val playlistID: String,
    val playlistName: String,
    val image: Int,
    val artist: Artist ?= null,
    val release: Int = 2023,
    val listMusic: ArrayList<Music> =  ArrayList(),
    val size: String = listMusic.size.toString()
)