package com.app.hearme.data

import com.app.hearme.R
import com.app.hearme.model.MoreSong

class MoreSongData {
    companion object {
        fun data(): ArrayList<MoreSong> {
            val data = ArrayList<MoreSong>()
            data.apply {
                add(MoreSong(R.drawable.ic_bold_heart, "Nothing"))
                add(MoreSong(R.drawable.ic_light_heart, "Like"))
                add(MoreSong(R.drawable.ic_add_playlist, "Add to Playlist"))
                add(MoreSong(R.drawable.ic_don_play, "Don't Play This"))
                add(MoreSong(R.drawable.ic_light_down, "Download"))
                add(MoreSong(R.drawable.ic_profile, "View Artist"))
                add(MoreSong(R.drawable.ic_playback, "Go to Album"))
                add(MoreSong(R.drawable.ic_check_box, "Mark as Played"))
                add(MoreSong(R.drawable.ic_podcast, "Go to Podcast"))
                add(MoreSong(R.drawable.ic_share, "Share"))
            }
            return data
        }
    }
}