package com.app.hearme.data.adapter

import android.app.ActionBar
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.app.hearme.MainActivity
import com.app.hearme.databinding.ViewListBrowseBinding
import com.app.hearme.model.Category
import kotlin.math.roundToInt

class CategoryAdapter(
    private val dataCategory: ArrayList<Category>,
    private val type: Int,
    val onItemClick: ((Bundle) -> Unit)? = null,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var count = 0

    companion object {
        const val EXPLORE = 0
        const val POPULAR_ARTISTS = 1
        const val SAVED = 2
    }

    override fun getItemViewType(position: Int): Int {
        return when (type) {
            0 -> EXPLORE
            1 -> POPULAR_ARTISTS
            else -> SAVED
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
            is ExploreViewHolder -> {
                holder.bind(dataCategory[position])
                holder.itemView.setOnClickListener {
                    val param = Bundle().apply {
                        putString("name", dataCategory[position].categoryName)
                        putInt("position", position)
                    }
                    onItemClick?.invoke(param)
                }
            }
        }
    }

    override fun getItemCount(): Int = dataCategory.size

    inner class ExploreViewHolder private constructor(
        val binding: ViewListBrowseBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        constructor(parent: ViewGroup) : this(
            ViewListBrowseBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        fun bind(category: Category) {
            binding.category = category
            val mainActivity = binding.containerView.context as MainActivity

            val paramsContainer = LinearLayout.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.WRAP_CONTENT
            )

            if (mainActivity.isLandscape()) {
                when (count) {
                    0 -> {
                        paramsContainer.apply {
                            marginEnd =
                                (12 * Resources.getSystem().displayMetrics.density).roundToInt()
                        }
                        count++
                    }
                    1 -> {
                        paramsContainer.apply {
                            marginEnd =
                                (12 * Resources.getSystem().displayMetrics.density).roundToInt()
                        }
                        count++
                    }
                    2 -> {
                        count = 0
                    }
                }
            } else {
                if (count == 0) {
                    paramsContainer.apply {
                        marginEnd =
                            (6 * Resources.getSystem().displayMetrics.density).roundToInt()
                    }
                    count++
                } else if (count == 1) {
                    paramsContainer.apply {
                        marginStart =
                            (6 * Resources.getSystem().displayMetrics.density).roundToInt()
                    }
                    count = 0
                }
            }
            binding.containerView.layoutParams = paramsContainer
        }
    }
}