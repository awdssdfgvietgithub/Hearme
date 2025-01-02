package com.app.hearme.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.hearme.databinding.ViewTopicSearchBinding
import com.app.hearme.model.TopicSearch

//class TopicSearchAdapter(private val data: ArrayList<TopicSearch>) :
//    RecyclerView.Adapter<TopicSearchAdapter.ViewHolder>() {
//    private var rowIndex = -1
//    private var first = true
//
//    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
//        val toggleButton: ToggleButton = itemView.findViewById(R.id.toggle_buttn_topic)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.view_topic_search, parent, false)
//        return ViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val mainActivity = holder.toggleButton.context as MainActivity
//
//        val itemsViewModel = data[position]
//        holder.toggleButton.text = itemsViewModel.name
//        holder.toggleButton.textOff = itemsViewModel.name
//        holder.toggleButton.textOn = itemsViewModel.name
//        holder.toggleButton.isChecked = itemsViewModel.isChecked
//
//        holder.toggleButton.setOnClickListener() {
//            for (i in 0 until data.size) {
//                update(i, false)
//                mainActivity.viewModelTopicSearch.updateChecked(i, false)
//            }
//            rowIndex = position
//            update(rowIndex, true)
//            mainActivity.viewModelTopicSearch.updateChecked(rowIndex, true)
//        }
//
//        holder.toggleButton.isChecked = rowIndex == position
//
//        if (first) {
//            holder.toggleButton.isChecked = itemsViewModel.isChecked
//            first = false
//        }
//    }
//
//    private fun update(position: Int, boolean: Boolean) {
//        data[position].isChecked = boolean
//        notifyDataSetChanged()
//    }
//
//    override fun getItemCount() = data.size
//}

class TopicSearchAdapter(private val data: ArrayList<TopicSearch>, private val type: Int ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val EXPLORE = 0
    }

    override fun getItemViewType(position: Int): Int {
        return when (type) {
            0 -> EXPLORE
            else -> EXPLORE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            EXPLORE -> ExploreViewHolder(parent)
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ExploreViewHolder -> holder.bind(data[position])
        }
    }

    override fun getItemCount(): Int = data.size

    inner class ExploreViewHolder private constructor(
        val binding: ViewTopicSearchBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        constructor(parent: ViewGroup) : this(
            ViewTopicSearchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        fun bind(topicSearch: TopicSearch) {
            binding.topicSearch = topicSearch
        }
    }
}