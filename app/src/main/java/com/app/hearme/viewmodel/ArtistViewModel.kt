package com.app.hearme.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.hearme.data.ArtistsData
import com.app.hearme.data.MusicsData
import com.app.hearme.model.Artist
import kotlin.collections.ArrayList

class ArtistViewModel : ViewModel() {
    private val _lstDataArtists = MutableLiveData<ArrayList<Artist>>()
    val lstDataArtists: LiveData<ArrayList<Artist>>
        get() = _lstDataArtists

    private lateinit var lst: ArrayList<Artist>

    init {
        getListDataArtists()
    }

    fun getListDataArtists() {
        lst = ArtistsData.dataArtist()
        lst.forEach {
            updateTotalNumberOfListeners(it.artistId)
        }
        _lstDataArtists.postValue(lst)
    }

    fun updateTotalNumberOfListeners(artistId: String) {
        lst.first { it.artistId == artistId }.apply {
            this.totalNumberOfListeners =
                MusicsData.dataMusic().filter { it.artist.artistId == this.artistId }
                    .sumOf { it.totalListeners }
        }
    }
}