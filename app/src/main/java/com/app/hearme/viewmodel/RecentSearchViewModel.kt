package com.app.hearme.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.hearme.data.RecentSearchesData
import com.app.hearme.model.RecentSearch

class RecentSearchViewModel : ViewModel() {
    private val _lstDataRecentSearch = MutableLiveData<ArrayList<RecentSearch>>()
    val lstDataRecentSearch: LiveData<ArrayList<RecentSearch>>
        get() = _lstDataRecentSearch

    private lateinit var lst: ArrayList<RecentSearch>

    init {
        getListDataRecentSearches()
    }

    private fun getListDataRecentSearches() {
        lst = RecentSearchesData.data()
        _lstDataRecentSearch.postValue(lst)
    }

    fun updateDataRecentSearch(query: String) {
        if (lst.none { it.name == query }) {
            lst.add(0, RecentSearch(query))
        } else {
            lst.removeIf { it.name == query }
            lst.add(0, RecentSearch(query))
        }
        _lstDataRecentSearch.postValue(lst)
    }

    fun delete(query: String) {
        lst.removeIf { it.name == query }
        _lstDataRecentSearch.postValue(lst)
    }

    fun deleteAll() {
        lst.removeAll(lst.toSet())
        _lstDataRecentSearch.postValue(lst)
    }
}