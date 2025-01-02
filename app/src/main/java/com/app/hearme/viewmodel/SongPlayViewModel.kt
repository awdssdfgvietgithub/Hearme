package com.app.hearme.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.hearme.model.Music

class SongPlayViewModel : ViewModel() {
    private val mutableSelectedItem = MutableLiveData<Music>()
    val selectedItem: LiveData<Music> get() = mutableSelectedItem

    fun selectItem(music: Music) {
        mutableSelectedItem.value = music
    }
}