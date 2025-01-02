package com.app.hearme.data.adapter

import android.graphics.drawable.PictureDrawable
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.caverock.androidsvg.SVG
import com.caverock.androidsvg.SVGParseException
import com.app.hearme.MainActivity
import com.app.hearme.model.User
import com.app.hearme.view.fragments.search.ExploreFragment
import com.app.hearme.view.tab_viewpager.TabFollowFragment
import com.app.hearme.R
import com.app.hearme.databinding.ViewProfileBinding
import com.app.hearme.databinding.ViewProfileV2Binding

class UserAdapterV2(
    private val dataUsers: ArrayList<User>,
    private val type: Int,
    private val fragment: Fragment,
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val SEARCH_PROFILES = 0
        const val RECOMMEND = 1
    }
    var onItemClickListener: ((userId: Int) -> Unit)? = null

    override fun getItemViewType(position: Int): Int {
        return when (type) {
            0 -> SEARCH_PROFILES
            else -> RECOMMEND
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            SEARCH_PROFILES -> SearchProfileViewHolder(parent)
            RECOMMEND -> RecommendViewHolder(parent)
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val mainActivity = fragment.context as MainActivity
        var destination: Int? = null
        when (holder) {
            is SearchProfileViewHolder, -> {
                holder.bind(dataUsers[position])
                if (fragment is ExploreFragment)
                    destination = R.id.action_item_nav_explore_to_viewDetailsProfileFragment
                else if (fragment is TabFollowFragment) {
                    destination = R.id.viewDetailsProfileFragment
                }
            }

            is RecommendViewHolder, -> {
                holder.bind(dataUsers[position])
                if (fragment is ExploreFragment)
                    destination = R.id.action_item_nav_explore_to_viewDetailsProfileFragment
                else if (fragment is TabFollowFragment) {
                    destination = R.id.viewDetailsProfileFragment
                }
            }
        }
        if (dataUsers[position].email != mainActivity.email && fragment is TabFollowFragment)
            holder.itemView.setOnClickListener() {
                if (destination != null) {
                    it.findNavController()
                        .navigate(destination,
                            Bundle().apply {
                                putString("userEmail", dataUsers[position].email)
                            }
                        )
                }
            }
    }

    override fun getItemCount(): Int = dataUsers.size

    inner class SearchProfileViewHolder private constructor(
        val binding: ViewProfileBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        constructor(parent: ViewGroup) : this(
            ViewProfileBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        fun bind(user: User) {
            binding.user = user

            val svgData = Base64.decode(user.avatarUrl, Base64.DEFAULT)
            val svgInputStream = svgData.inputStream()

            try {
                val svg = SVG.getFromInputStream(svgInputStream)
                val drawable = PictureDrawable(svg.renderToPicture())
                binding.imageViewProfile.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
                binding.imageViewProfile.setImageDrawable(drawable)
            } catch (e: SVGParseException) {
                Log.e("SVGDecode", "Error decoding SVG", e)
            }

           binding.root.setOnClickListener() {
                onItemClickListener?.invoke(user.userId)
            }
        }
    }

    inner class RecommendViewHolder private constructor(
        val binding: ViewProfileV2Binding,
    ) : RecyclerView.ViewHolder(binding.root) {
        constructor(parent: ViewGroup) : this(
            ViewProfileV2Binding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        fun bind(user: User) {
            binding.user = user

            val svgData = Base64.decode(user.avatarUrl, Base64.DEFAULT)
            val svgInputStream = svgData.inputStream()

            try {
                val svg = SVG.getFromInputStream(svgInputStream)
                val drawable = PictureDrawable(svg.renderToPicture())
                binding.imageViewProfile.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
                binding.imageViewProfile.setImageDrawable(drawable)
            } catch (e: SVGParseException) {
                Log.e("SVGDecode", "Error decoding SVG", e)
            }

            binding.root.setOnClickListener() {
                onItemClickListener?.invoke(user.userId)
            }
        }
    }
}