package com.app.hearme.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.app.hearme.MainActivity
import com.app.hearme.databinding.ViewRecentSearchBinding
import com.app.hearme.model.RecentSearch

class RecentSearchesAdapter(
    private val dataSearches: ArrayList<RecentSearch>,
) :
    RecyclerView.Adapter<RecentSearchesAdapter.SearchViewHolder>() {
    private lateinit var binding: ViewRecentSearchBinding
    private lateinit var recentSearchAdapterCallBack: RecentSearchAdapterCallBack

    fun setCallBack(recentSearchAdapterCallBack: RecentSearchAdapterCallBack) {
        this.recentSearchAdapterCallBack = recentSearchAdapterCallBack
    }

    inner class SearchViewHolder(binding: ViewRecentSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(recentSearch: RecentSearch) {
            binding.recentSearch = recentSearch
            binding.imageViewDelete.setOnClickListener {
                val mainActivity = it.context as MainActivity
                val position = absoluteAdapterPosition
                Toast.makeText(it.context, "$position", Toast.LENGTH_SHORT).show()
                mainActivity.viewModelRecentSearch.delete(dataSearches[position].name)
            }
            binding.textRecentSearchName.setOnClickListener() {
                val position = absoluteAdapterPosition
                recentSearchAdapterCallBack.setQuery(dataSearches[position].name)
            }
        }

        private fun remove(it: View?, position: Int) {
            if (position >= 0)
                dataSearches.removeAt(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        binding =
            ViewRecentSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(dataSearches[position])
    }

    override fun getItemCount() = dataSearches.size

    override fun getItemViewType(position: Int): Int {
        return position
    }

    interface RecentSearchAdapterCallBack {
        fun setQuery(name: String)
    }
}