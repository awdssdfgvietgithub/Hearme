package com.app.hearme.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.hearme.data.ArtistsData
import com.app.hearme.data.CategoriesData
import com.app.hearme.data.FakeData
import com.app.hearme.data.MusicsData
import com.app.hearme.data.PlaylistData
import com.app.hearme.model.Artist
import com.app.hearme.model.Category
import com.app.hearme.model.Chart
import com.app.hearme.model.Music
import com.app.hearme.model.Playlist

class HomeViewModel : ViewModel() {

    private val _lstDataMusic = MutableLiveData<List<Music>>()
    private val _lstDataArtist = MutableLiveData<List<Artist>>()
    private val _lstDataChart = MutableLiveData<List<Chart>>()
    private val _lstDataCategory = MutableLiveData<List<Category>>()
    private val _lstDataPlaylist = MutableLiveData<List<Playlist>>()

    val lstDataMusic: LiveData<List<Music>>
        get() = _lstDataMusic
    val lstDataArtist: LiveData<List<Artist>>
        get() = _lstDataArtist
    val lstDataChart: LiveData<List<Chart>>
        get() = _lstDataChart
    val lstDataCategory: LiveData<List<Category>>
        get() = _lstDataCategory
    val lstDataPlaylist: LiveData<List<Playlist>>
        get() = _lstDataPlaylist


    private lateinit var lstMusic: ArrayList<Music>
    private lateinit var lstArtist: ArrayList<Artist>
    private lateinit var lstChart: ArrayList<Chart>
    private lateinit var lstCategory: ArrayList<Category>
    private lateinit var lstPlaylist: ArrayList<Playlist>

    init {
        getListDataMusic()
        getListDataArtist()
        getListDataChart()
        getListDataCategory()
    }

    fun getListDataMusic() {
        lstMusic = MusicsData.dataMusic()
        _lstDataMusic.postValue(lstMusic)
    }

    fun getListDataArtist() {
        lstArtist = ArtistsData.dataArtist()
        _lstDataArtist.postValue(lstArtist)
    }

    fun getListDataChart() {
        lstChart = FakeData.dataChart()
        _lstDataChart.postValue(lstChart)
    }

    fun getListDataPlaylist() {
        lstPlaylist = PlaylistData.dataPlaylist()
        _lstDataPlaylist.postValue(lstPlaylist)
    }

    private fun getListDataCategory() {
        lstCategory = CategoriesData.dataCategory()
        _lstDataCategory.postValue(lstCategory)
    }
}