package com.app.hearme.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.hearme.data.PlaylistData
import com.app.hearme.model.Playlist

class PlaylistViewModel: ViewModel() {
    private val _lstDataPlaylist = MutableLiveData<ArrayList<Playlist>>()
    val lstDataPlaylist: LiveData<ArrayList<Playlist>>
        get() = _lstDataPlaylist

    private lateinit var lst: ArrayList<Playlist>

    init {
        getListDataPlaylist()
    }

    private fun getListDataPlaylist() {
        lst = PlaylistData.dataPlaylist()
        _lstDataPlaylist.postValue(lst)
    }

//    fun addDataPlaylist(id: String, name: String, img: Int, lstMusic: ArrayList<Music>) {
//        lst.add( Playlist( id, name, img, lstMusic ) )
//        _lstDataPlaylist.postValue(lst)
//    }
}