package com.app.hearme.data

import com.app.hearme.R
import com.app.hearme.model.Music
import com.app.hearme.model.Playlist

class PlaylistData {
    companion object {
        fun dataPlaylist(): ArrayList<Playlist> {
            val dataPlaylist = ArrayList<Playlist>()
            dataPlaylist.apply {
                add(
                    Playlist(
                        "pl001",
                        "My Favorite Pop Songs",
                        R.drawable.img_music,
                        ArtistsData.dataArtist().first { it.artistId == "ar001" },
                        2022,
                        MusicsData.dataMusic()
                            .filter { it.musicID == "ms001" || it.musicID == "ms002" || it.musicID == "ms005" || it.musicID == "ms008" } as ArrayList<Music>
                    )
                )
                add(
                    Playlist(
                        "pl002",
                        "90s Old Song",
                        R.drawable.img_music,
                        ArtistsData.dataArtist().first { it.artistId == "ar007" },
                        2022,
                        MusicsData.dataMusic()
                            .filter { it.musicID == "ms004" || it.musicID == "ms003" || it.musicID == "ms006" || it.musicID == "ms008" } as ArrayList<Music>
                    )
                )
                add(
                    Playlist(
                        "pl003",
                        "Legend Rock Song",
                        R.drawable.img_music,
                        ArtistsData.dataArtist().first { it.artistId == "ar004" },
                        2023,
                        MusicsData.dataMusic()
                            .filter { it.musicID == "ms011" || it.musicID == "ms010" || it.musicID == "ms005" || it.musicID == "ms003" } as ArrayList<Music>
                    )
                )
            }
            return dataPlaylist
        }
    }
}