package com.app.hearme.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.hearme.data.MusicsData
import com.app.hearme.model.Music

class MusicViewModel : ViewModel() {
    private val _lstDataMusics = MutableLiveData<ArrayList<Music>>()
    val lstDataMusics: LiveData<ArrayList<Music>>
        get() = _lstDataMusics

    private lateinit var lst: ArrayList<Music>

    init {
        getListDataMusics()
    }

    private fun getListDataMusics() {
        lst = MusicsData.dataMusic()
        _lstDataMusics.postValue(lst)
    }

    fun updatePlaying(music: Music, isPlaying: Boolean) {
        lst.forEach {
            it.isPlaying = false
        }
        lst.first { it.musicID == music.musicID }.apply {
            this.isPlaying = isPlaying
        }
        _lstDataMusics.postValue(lst)
    }
}