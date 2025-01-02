package com.app.hearme.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.hearme.data.TopicSearchData
import com.app.hearme.model.TopicSearch

class TopicSearchViewModel : ViewModel() {
    private val _lstDataTopicSearch = MutableLiveData<ArrayList<TopicSearch>>()

    private lateinit var lst: ArrayList<TopicSearch>

    init {
        getListDataTopicSearch()
    }

    fun getTopicSearch(): MutableLiveData<ArrayList<TopicSearch>> {
        return _lstDataTopicSearch
    }

    private fun getListDataTopicSearch() {
        lst = TopicSearchData.data()
        _lstDataTopicSearch.value = lst
    }

    fun updateChecked(name: String) {
        lst.forEach {
            it.isChecked = false
        }
        lst.first { it.name == name }.isChecked = true
        _lstDataTopicSearch.value = lst
    }
}