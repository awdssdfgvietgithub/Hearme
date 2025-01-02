package com.app.hearme.data.adapter

import android.app.ActionBar.LayoutParams
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.app.hearme.MainActivity
import com.app.hearme.R
import com.app.hearme.databinding.*
import com.app.hearme.model.Music
import com.app.hearme.view.fragments.homeactionmenu.HomeFragment
import com.app.hearme.view.fragments.homeactionmenu.TrendingNowFragment
import com.app.hearme.view.fragments.library.MyLibraryFragment
import com.app.hearme.view.fragments.search.*
import com.app.hearme.view.tab_viewpager.TabPodcastFragment
import kotlin.math.roundToInt


class MusicAdapter(
    private val dataMusics: ArrayList<Music>,
    private val type: Int,
    private val fragment: Fragment,
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var count = 0

    companion object {
        const val HOME = 0
        const val SONG_LIST = 1
        const val PODCAST_LIST = 2
        const val MUSIC_LIST = 3
        const val ALBUM_LIST = 4
        const val LIBRARY_SONG_LIST = 5
        const val SONG_LIST_2 = 6
    }

    override fun getItemViewType(position: Int): Int {
        return when (type) {
            0 -> HOME
            1 -> SONG_LIST
            2 -> PODCAST_LIST
            3 -> MUSIC_LIST
            4 -> ALBUM_LIST
            5 -> LIBRARY_SONG_LIST
            6 -> SONG_LIST_2
            else -> MUSIC_LIST
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HOME -> HomeViewHolder(parent)
            SONG_LIST -> SongNotificationViewHolder(parent)
            PODCAST_LIST -> PodcastNotificationViewHolder(parent)
            MUSIC_LIST -> TopMusicViewHolder(parent)
            ALBUM_LIST -> AlbumViewHolder(parent)
            LIBRARY_SONG_LIST -> LibraryListSongViewHolder(parent)
            SONG_LIST_2 -> SongList2ViewHolder(parent)
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount(): Int = dataMusics.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var destination: Int? = null
        when (holder) {
            is HomeViewHolder -> {
                holder.bind(dataMusics[position])
                if (fragment is HomeFragment)
                    destination = R.id.action_item_nav_home_to_viewDetailsSongFragment
                else if (fragment is TrendingNowFragment)
                    destination = R.id.action_trendingNowFragment_to_viewDetailsSongFragment
                if (fragment is MyLibraryFragment)
                    destination = R.id.action_item_nav_library_to_viewDetailsSongFragment
            }
            is SongNotificationViewHolder -> {
                holder.bind(dataMusics[position])
                destination = R.id.action_notificationFragment_to_viewDetailsSongFragment
            }
            is PodcastNotificationViewHolder -> {
                holder.bind(dataMusics[position])
                when (fragment) {
                    is ExploreFragment -> {
                        destination = R.id.action_item_nav_explore_to_viewDetailsPodcastFragment
                    }
                    is ViewDetailsArtistOfPodcastFragment -> {
                        destination =
                            R.id.action_viewDetailsArtistOfPodcastFragment_to_viewDetailsPodcastFragment
                    }
                    is TabPodcastFragment -> {
                        destination = R.id.action_notificationFragment_to_viewDetailsPodcastFragment
                    }
                }
            }
            is LibraryListSongViewHolder -> {
                holder.bind(dataMusics[position])
            }
            is TopMusicViewHolder -> {
                holder.bind(dataMusics[position])
                destination = R.id.action_item_nav_explore_to_viewDetailsSongFragment
            }
            is AlbumViewHolder -> {
                holder.bind(dataMusics[position])
                destination = R.id.action_item_nav_explore_to_viewDetailsAlbumFragment
            }
            is SongList2ViewHolder -> {
                holder.bind(dataMusics[position])
                when (fragment) {
                    is ExploreFragment -> destination =
                        R.id.action_item_nav_explore_to_viewDetailsSongFragment
                    is ViewDetailsSongFragment -> {
                        destination =
                            R.id.viewDetailsSongFragment
                    }
                    is ViewDetailsArtistFragment -> {
                        destination =
                            R.id.action_viewDetailsArtistFragment_to_viewDetailsSongFragment
                    }
                    is ViewDetailsAlbumFragment -> {
                        destination =
                            R.id.action_viewDetailsAlbumFragment_to_viewDetailsSongFragment
                    }
                }
            }
        }
        holder.itemView.setOnClickListener() {
            if (destination != null) {
                it.findNavController()
                    .navigate(destination,
                        Bundle().apply {
                            putString("musicID", dataMusics[position].musicID)
                        }
                    )
            }
        }
    }

    inner class SongList2ViewHolder private constructor(
        val binding: ViewListSong2Binding,
    ) : RecyclerView.ViewHolder(binding.root) {
        constructor(parent: ViewGroup) : this(
            ViewListSong2Binding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        fun bind(music: Music) {
            binding.music = music

            val mainActivity = binding.spinnerDropDownMore.context as MainActivity
            mainActivity.initSpinnerMore(
                binding.spinnerDropDownMore,
                dataMusics[absoluteAdapterPosition],
                0,
                fragment
            )
        }
    }

    inner class HomeViewHolder private constructor(
        val binding: ViewHomeTrendingBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        constructor(parent: ViewGroup) : this(
            ViewHomeTrendingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        fun bind(music: Music) {
            binding.music = music

        }
    }

    inner class SongNotificationViewHolder private constructor(
        val binding: ViewListSongBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        constructor(parent: ViewGroup) : this(
            ViewListSongBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        fun bind(music: Music) {
            binding.music = music

            val mainActivity = binding.spinnerDropDownMore.context as MainActivity
            mainActivity.initSpinnerMore(
                binding.spinnerDropDownMore,
                dataMusics[absoluteAdapterPosition],
                0,
                fragment
            )
        }
    }

    inner class PodcastNotificationViewHolder private constructor(
        val binding: ViewListPodcastBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        constructor(parent: ViewGroup) : this(
            ViewListPodcastBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        fun bind(music: Music) {
            binding.music = music

            val mainActivity = binding.spinnerDropDownMore.context as MainActivity
            mainActivity.initSpinnerMore(
                binding.spinnerDropDownMore,
                dataMusics[absoluteAdapterPosition],
                2,
                fragment
            )
        }
    }

    inner class TopMusicViewHolder private constructor(
        val binding: ViewTopListMusicBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        constructor(parent: ViewGroup) : this(
            ViewTopListMusicBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        fun bind(music: Music) {
            binding.music = music

            val mainActivity = binding.spinnerDropDownMore.context as MainActivity
            mainActivity.initSpinnerMore(
                binding.spinnerDropDownMore,
                dataMusics[absoluteAdapterPosition],
                0,
                fragment
            )
        }
    }

    inner class AlbumViewHolder private constructor(
        val binding: ViewAlbumBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        constructor(parent: ViewGroup) : this(
            ViewAlbumBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        fun bind(music: Music) {
            binding.music = music
            val mainActivity = binding.containerView.context as MainActivity
            val position = absoluteAdapterPosition

            val paramsContainer = LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
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

    inner class LibraryListSongViewHolder private constructor(
        val binding: ViewListSongLibraryBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        constructor(parent: ViewGroup) : this(
            ViewListSongLibraryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        fun bind(music: Music) {
            binding.music = music

            val mainActivity = binding.spinnerDropDownMore.context as MainActivity
            mainActivity.initSpinnerMore(
                binding.spinnerDropDownMore,
                dataMusics[absoluteAdapterPosition],
                0,
                fragment
            )
        }
    }
}