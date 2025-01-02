package com.app.hearme.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.hearme.databinding.ViewHomeChartBinding
import com.app.hearme.model.Chart

class ChartAdapter (private val dataChart: ArrayList<Chart>, private val type: Int ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val HOME = 0
        const val POPULAR_ARTISTS = 1
        const val SAVED = 2
    }

    override fun getItemViewType(position: Int): Int {
        return when (type) {
            0 -> HOME
            1 -> POPULAR_ARTISTS
            else -> SAVED
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HOME -> HomeViewHolder(parent)
//            TOPIC_LIST -> InterestViewHolder(parent)
//            SAVED -> SavedViewHolder(parent)
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HomeViewHolder -> holder.bind(dataChart[position])
        }
    }

    override fun getItemCount(): Int = dataChart.size

    inner class HomeViewHolder private constructor(
        val binding: ViewHomeChartBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        constructor(parent: ViewGroup) : this(
            ViewHomeChartBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        fun bind(chart: Chart) {
            binding.chart = chart
        }
    }
}