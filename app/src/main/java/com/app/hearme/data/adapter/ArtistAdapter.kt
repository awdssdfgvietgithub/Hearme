package com.app.hearme.data.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.app.hearme.R
import com.app.hearme.databinding.ViewHomeArtistBinding
import com.app.hearme.databinding.ViewItemArtistBinding
import com.app.hearme.databinding.ViewPodcastAndShowBinding
import com.app.hearme.databinding.ViewListArtistBinding
import com.app.hearme.model.Artist
import com.app.hearme.view.fragments.accountssetup.FollowArtistsFragment
import com.app.hearme.view.fragments.homeactionmenu.HomeFragment
import com.app.hearme.view.fragments.homeactionmenu.PopularArtistsFragment

class ArtistAdapter(
    private val dataArtists: ArrayList<Artist>,
    private val type: Int,
    private val fragment: Fragment? = null,
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val HOME = 0
        const val POPULAR_ARTISTS = 1
        const val SAVED = 2

        //        const val FOLLOW_ARTISTS = 3
        const val ARTISTS_LIST = 4
        const val PODCAST_AND_SHOW = 5
        const val ARTISTS_LIB = 6
    }

    override fun getItemViewType(position: Int): Int {
        return when (type) {
            0 -> HOME
            1 -> POPULAR_ARTISTS
//            3 -> FOLLOW_ARTISTS
            4 -> ARTISTS_LIST
            5 -> PODCAST_AND_SHOW
            6 -> ARTISTS_LIB
            else -> SAVED
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HOME -> HomeViewHolder(parent)
//            TOPIC_LIST -> InterestViewHolder(parent)
//            SAVED -> SavedViewHolder(parent)
//            FOLLOW_ARTISTS -> ArtistViewHolderFollowArtists(parent)
            ARTISTS_LIST -> ListArtistViewHolder(parent)
            PODCAST_AND_SHOW -> PodcastAndShowViewHolder(parent)
            ARTISTS_LIB -> ArtistViewHolder(parent)
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var destination: Int? = null
        when (holder) {
            is HomeViewHolder -> {
                holder.bind(dataArtists[position])
                when (fragment) {
                    is PopularArtistsFragment -> {
                        destination =
                            R.id.action_popularArtistsFragment_to_viewDetailsArtistFragment
                    }
                    is HomeFragment -> {
                        destination = R.id.action_item_nav_home_to_viewDetailsArtistFragment

                    }
                }
            }
//            is ArtistViewHolderFollowArtists -> holder.bind(dataArtists[position])
            is ListArtistViewHolder -> {
                holder.bind(dataArtists[position])
                if (fragment is FollowArtistsFragment) {

                } else
                    destination = R.id.action_item_nav_explore_to_viewDetailsArtistFragment
            }
            is PodcastAndShowViewHolder -> {
                holder.bind(dataArtists[position])
                destination = R.id.action_item_nav_explore_to_viewDetailsArtistOfPodcastFragment
            }
            is ArtistViewHolder -> {
                holder.bind(dataArtists[position])
            }
        }
        holder.itemView.setOnClickListener() {
            if (destination != null) {
                it.findNavController()
                    .navigate(destination,
                        Bundle().apply {
                            putString("artistID", dataArtists[position].artistId)
                        }
                    )
            }
        }
    }

    override fun getItemCount(): Int = dataArtists.size

    inner class HomeViewHolder private constructor(
        val binding: ViewHomeArtistBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        constructor(parent: ViewGroup) : this(
            ViewHomeArtistBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        fun bind(artist: Artist) {
            binding.artist = artist
        }
    }

//    inner class ArtistViewHolderFollowArtists private constructor(
//        val binding: ViewFollowArtistsBinding,
//    ) : RecyclerView.ViewHolder(binding.root) {
//        constructor(parent: ViewGroup) : this(
//            ViewFollowArtistsBinding.inflate(
//                LayoutInflater.from(parent.context),
//                parent,
//                false
//            )
//        )
//
//        fun bind(artist: Artist) {
//            binding.artist = artist
//
//            binding.toggleButtonFollow.setOnCheckedChangeListener() { compound, isChecked ->
//                val activity = compound.context as MainActivity
//                userViewModel.updateFollowingArtists(
//                    activity.email,
//                    dataArtists[absoluteAdapterPosition],
//                    isChecked
//                )
//            }
//        }
//    }

    inner class ListArtistViewHolder private constructor(
        val binding: ViewListArtistBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        constructor(parent: ViewGroup) : this(
            ViewListArtistBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        fun bind(artist: Artist) {
            binding.artist = artist
        }
    }

    inner class PodcastAndShowViewHolder private constructor(
        val binding: ViewPodcastAndShowBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        constructor(parent: ViewGroup) : this(
            ViewPodcastAndShowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        fun bind(artist: Artist) {
            binding.artist = artist
        }
    }

//    inner class InterestViewHolder private constructor(
//        val binding: ViewTopicInterestBinding
//    ) : RecyclerView.ViewHolder(binding.root) {
//
//        constructor(parent: ViewGroup) : this(
//            ViewTopicInterestBinding.inflate(
//                LayoutInflater.from(parent.context),
//                parent,
//                false
//            )
//        )
//
//        fun bind(topic: Topic) {
//            binding.topic = topic
//        }
//    }

    inner class ArtistViewHolder private constructor(
        val binding: ViewItemArtistBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        constructor(parent: ViewGroup) : this(
            ViewItemArtistBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        fun bind(artist: Artist) {
            binding.artist = artist
        }
    }

}