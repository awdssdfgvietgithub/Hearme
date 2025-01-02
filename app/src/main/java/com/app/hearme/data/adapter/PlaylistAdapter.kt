package com.app.hearme.data.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.hearme.databinding.ViewItemAlbumBinding
import com.app.hearme.databinding.ViewItemPlaylistBinding
import com.app.hearme.databinding.ViewListPlaylistBinding
import com.app.hearme.model.Playlist

class PlaylistAdapter(
    private val dataPlaylist: ArrayList<Playlist>,
    private val type: Int,
    private val onItemClick: ((Bundle) -> Unit)? = null,
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val LIBRARY = 0
        const val PROFILE_DETAIL = 1
        const val ALBUMS = 2
    }

    override fun getItemViewType(position: Int): Int {
        return when (type) {
            0 -> LIBRARY
            1 -> PROFILE_DETAIL
            2 -> ALBUMS
            else -> LIBRARY
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            LIBRARY -> LibraryViewHolder(parent)
            PROFILE_DETAIL -> ProfileDetailViewHolder(parent)
            ALBUMS -> AlbumsViewHolder(parent)
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is LibraryViewHolder -> {
                holder.bind(dataPlaylist[position])
                holder.itemView.setOnClickListener {
                    val param = Bundle().apply {
                        putInt("img", dataPlaylist[position].image)
                        putString("id", dataPlaylist[position].playlistID)
                        putString("name", dataPlaylist[position].playlistName)
                        putString("size", dataPlaylist[position].size)
//                        putInt("position", position)
                    }
                    onItemClick?.invoke(param)
                }
            }
            is ProfileDetailViewHolder -> {
                holder.bind(dataPlaylist[position])
            }
            is AlbumsViewHolder -> {
                holder.bind(dataPlaylist[position])
            }
        }
    }

    override fun getItemCount(): Int = dataPlaylist.size

    inner class LibraryViewHolder private constructor(
        val binding: ViewListPlaylistBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        constructor(parent: ViewGroup) : this(
            ViewListPlaylistBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        fun bind(playlist: Playlist) {
            binding.playlist = playlist
        }
    }

    inner class ProfileDetailViewHolder private constructor(
        val binding: ViewItemPlaylistBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        constructor(parent: ViewGroup) : this(
            ViewItemPlaylistBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        fun bind(playlist: Playlist) {
            binding.playlist = playlist
        }
    }

    inner class AlbumsViewHolder private constructor(
        val binding: ViewItemAlbumBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        constructor(parent: ViewGroup) : this(
            ViewItemAlbumBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        fun bind(playlist: Playlist) {
            binding.playlist = playlist
        }
    }
}