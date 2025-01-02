package com.app.hearme.data

import com.app.hearme.model.Artist
import com.app.hearme.model.Music
import com.app.hearme.model.Playlist
import com.app.hearme.model.User
import java.util.*
import kotlin.collections.ArrayList

class UsersData {
    companion object {
        fun data(): ArrayList<User> {
            val data = ArrayList<User>()
            data.apply {
                add(
                    User(
                        "phuongviet.huit@gmail.com",
                        "phuongviet123456",
                        "awdssdfgviethufi@gmail.com",
                        null,
                        "https://firebasestorage.googleapis.com/v0/b/hearme-app-16567.appspot.com/o/images%2Fusers%2Fuser_phuongviet.huit%40gmail.com.png?alt=media&token=584ab9c3-4b4b-461d-b7ea-e263e3cd2123&_gl=1*11vbk9i*_ga*MTUyOTk3NDI1NS4xNjkzMzU5NjY4*_ga_CW55HF8NVT*MTY5NTk2MDMwNi4xMS4xLjE2OTU5NjIxMTkuMzIuMC4w",
                        "Nguyen Phuong Viet",
                        "Viet Huit",
                        Date("06/12/2002"),
                        "VN",
                        "93 982 63 76",
                        1234,
                        true, //true is male, false is female
                        true,
                        false, // true if user the first sign in
                        ArtistsData.dataArtist() //listArtistsFollowing
                            .filter { it.artistId == " ar001" || it.artistId == "ar002" || it.artistId == "ar004" } as ArrayList<Artist>,
                        ArrayList(), //listFollowers
                        MusicsData.dataMusic() //listMusicsLoved
                            .filter { it.musicID == "ms001" || it.musicID == "ms002" || it.musicID == "ms005" || it.musicID == "ms008" } as ArrayList<Music>,
                        MusicsData.dataMusic() //listMusicsListened
                            .filter { it.musicID == "ms001" || it.musicID == "ms002" || it.musicID == "ms005" || it.musicID == "ms008" || it.musicID == "ms011" } as ArrayList<Music>,
                        MusicsData.dataMusic() //listMusicsDownloaded
                            .filter { it.musicID == "ms004" || it.musicID == "ms009" } as ArrayList<Music>,
                        ArrayList(), //listMusicsQueued
                        PlaylistData.dataPlaylist()
                            .filter { it.playlistID == "pl001" || it.playlistID == "pl003" } as ArrayList<Playlist>,
                        ArrayList() //blackListMusic
                    )
                )
                add(
                    User(
                        "lebuitantruong@gmail.com",
                        "lbtt2801",
                        "truongletanbui@gmail.com",
                        null,
                        "https://firebasestorage.googleapis.com/v0/b/hearme-app-16567.appspot.com/o/images%2Fusers%2Fuser_lebuitantruong%40gmail.com.png?alt=media&token=5775defa-8f4d-4dd4-a52e-47f3db38d4a5&_gl=1*1pft1yg*_ga*MTUyOTk3NDI1NS4xNjkzMzU5NjY4*_ga_CW55HF8NVT*MTY5NTk2MDMwNi4xMS4xLjE2OTU5NjIwODkuMi4wLjA.",
                        "Le Bui Tan Truong",
                        "Truong HUIT",
                        Date("28/12/2002"),
                        "VN",
                        "32 846 79 24",
                        4567,
                        true, // true is male, false is female,
                        true,
                        false, // true if user the first sign in
                        ArtistsData.dataArtist()//listArtistsFollowing
                            .filter { it.artistId == " ar003" || it.artistId == "ar004" } as ArrayList<Artist>,
                        ArrayList(),
                        MusicsData.dataMusic() //listMusicsLoved
                            .filter { it.musicID == "ms002" || it.musicID == "ms006" || it.musicID == "ms007" } as ArrayList<Music>,
                        MusicsData.dataMusic() //listMusicListened
                            .filter { it.musicID == "ms002" || it.musicID == "ms006" || it.musicID == "ms007" || it.musicID == "ms001" || it.musicID == "ms004" } as ArrayList<Music>,
                        MusicsData.dataMusic() //listMusicDownloaded
                            .filter { it.musicID == "ms003" || it.musicID == "ms012" || it.musicID == "ms009" } as ArrayList<Music>,
                        MusicsData.dataMusic() //listMusicsQueued
                            .filter { it.musicID == "ms002" || it.musicID == "ms006" } as ArrayList<Music>,
                        PlaylistData.dataPlaylist() //listMusicsPlaylist
                            .filter { it.playlistID == "pl001" || it.playlistID == "pl002" || it.playlistID == "pl003" } as ArrayList<Playlist>,
                        ArrayList() //blackListMusic
                    )
                )
                add(
                    User(
                        "jennywilson@gmail.com",
                        "123456",
                        "jennywilson2@gmail.com",
                        null,
                        "https://firebasestorage.googleapis.com/v0/b/hearme-app-16567.appspot.com/o/images%2Fusers%2Fuser_jennywilson%40gmail.com.png?alt=media&token=77d90636-364b-40b1-a01a-62d5fc29b486&_gl=1*1jueyc9*_ga*MTUyOTk3NDI1NS4xNjkzMzU5NjY4*_ga_CW55HF8NVT*MTY5NTk2MDMwNi4xMS4xLjE2OTU5NjIwNTUuMzYuMC4w",
                        "Jenny Wilson",
                        "Wilson",
                        Date("28/01/2002"),
                        "VN",
                        "32 846 79 25",
                        4567,
                        true, // true is male, false is female,
                        false,
                        false, // true if user the first sign in
                        ArtistsData.dataArtist()//listArtistsFollowing
                            .filter { it.artistId == " ar005" || it.artistId == "ar006" } as ArrayList<Artist>,
                        ArrayList(),
//                        UsersData.data() //listFollowers
//                            .filter { it.email == "phuongviet.huit@gmail.com" || it.email == "lebuitantruong@gmail.com" } as ArrayList<User>,
                        MusicsData.dataMusic() //listMusicsLoved
                            .filter { it.musicID == "ms009" || it.musicID == "ms010" || it.musicID == "ms011" } as ArrayList<Music>,
                        MusicsData.dataMusic() //listMusicListened
                            .filter { it.musicID == "ms009" || it.musicID == "ms007" || it.musicID == "ms005" || it.musicID == "ms010" || it.musicID == "ms011" } as ArrayList<Music>,
                        MusicsData.dataMusic() //listMusicDownloaded
                            .filter { it.musicID == "ms005" || it.musicID == "ms004" || it.musicID == "ms001" } as ArrayList<Music>,
                        ArrayList(), //listMusicsQueued
                        PlaylistData.dataPlaylist()
                            .filter { it.playlistID == "pl001" || it.playlistID == "pl002" } as ArrayList<Playlist>,
//                        MusicsData.dataMusic() //listPlayedMusic
//                            .filter { it.musicID == "ms002" || it.musicID == "ms003" || it.musicID == "ms007" } as ArrayList<Music>,
                        ArrayList() //blackListMusic
                    )
                )
                add(
                    User(
                        "jennyfoose@gmail.com",
                        "123456",
                        "jennyfoose2@gmail.com",
                        null,
                        "https://firebasestorage.googleapis.com/v0/b/hearme-app-16567.appspot.com/o/images%2Fusers%2Fuser_jennyfoose%40gmail.com.png?alt=media&token=fd83d52f-5ec8-4660-922d-522d360a691d&_gl=1*t9u4gk*_ga*MTUyOTk3NDI1NS4xNjkzMzU5NjY4*_ga_CW55HF8NVT*MTY5NTk2MDMwNi4xMS4xLjE2OTU5NjIwMDkuMTUuMC4w",
                        "Jenny Foose",
                        "Foo",
                        Date("28/01/2002"),
                        "VN",
                        "32 846 79 25",
                        4567,
                        true, // true is male, false is female,
                        false,
                        false, // true if user the first sign in
                    )
                )
                add(
                    User(
                        "jennyhanlin@gmail.com",
                        "123456",
                        "jennyhanlin2@gmail.com",
                        null,
                        "https://firebasestorage.googleapis.com/v0/b/hearme-app-16567.appspot.com/o/images%2Fusers%2Fuser_jennyhanlin%40gmail.com.png?alt=media&token=cf48ea32-e493-4039-94fb-2259ce48b2ba&_gl=1*1xmaock*_ga*MTUyOTk3NDI1NS4xNjkzMzU5NjY4*_ga_CW55HF8NVT*MTY5NTk2MDMwNi4xMS4xLjE2OTU5NjIwMTkuNS4wLjA.",
                        "Jenny Hanlin",
                        "Hanlin",
                        Date("28/01/2002"),
                        "VN",
                        "32 846 79 25",
                        4567,
                        true, // true is male, false is female,
                        false,
                        false, // true if user the first sign in
                    )
                )
                add(
                    User(
                        "jennywinkles@gmail.com",
                        "123456",
                        "jennywinkles2@gmail.com",
                        null,
                        "https://firebasestorage.googleapis.com/v0/b/hearme-app-16567.appspot.com/o/images%2Fusers%2Fuser_jennywinkles%40gmail.com.png?alt=media&token=0781689e-92ca-4506-84cc-338adda41358&_gl=1*1myuikt*_ga*MTUyOTk3NDI1NS4xNjkzMzU5NjY4*_ga_CW55HF8NVT*MTY5NTk2MDMwNi4xMS4xLjE2OTU5NjIwNjUuMjYuMC4w",
                        "Jenny winkles",
                        "Win",
                        Date("28/01/2002"),
                        "VN",
                        "32 846 79 25",
                        4567,
                        true, // true is male, false is female,
                        false,
                        false, // true if user the first sign in
                    )
                )
                add(
                    User(
                        "jennyballentine@gmail.com",
                        "123456",
                        "jennyballentine2@gmail.com",
                        null,
                        "https://firebasestorage.googleapis.com/v0/b/hearme-app-16567.appspot.com/o/images%2Fusers%2Fuser_jennyballentine%40gmail.com.png?alt=media&token=d1a96764-0f4a-4647-9b39-777a678f673b&_gl=1*f1dlt*_ga*MTUyOTk3NDI1NS4xNjkzMzU5NjY4*_ga_CW55HF8NVT*MTY5NTk2MDMwNi4xMS4xLjE2OTU5NjE5OTYuMjguMC4w",
                        "Jenny Ballentine",
                        "Ballentine",
                        Date("28/01/2002"),
                        "VN",
                        "32 846 79 25",
                        4567,
                        true, // true is male, false is female,
                        false,
                        false, // true if user the first sign in
                    )
                )
                add(
                    User(
                        "jennywigington@gmail.com",
                        "123456",
                        "jennywigington2@gmail.com",
                        null,
                        "https://firebasestorage.googleapis.com/v0/b/hearme-app-16567.appspot.com/o/images%2Fusers%2Fuser_jennywigington%40gmail.com.png?alt=media&token=2ee0ad9e-b1d6-4882-9a36-a3cad8975764&_gl=1*1gk1rrj*_ga*MTUyOTk3NDI1NS4xNjkzMzU5NjY4*_ga_CW55HF8NVT*MTY5NTk2MDMwNi4xMS4xLjE2OTU5NjIwNDQuNDcuMC4w",
                        "Jenny Wigington",
                        "Wigington",
                        Date("28/01/2002"),
                        "VN",
                        "32 846 79 25",
                        4567,
                        true, // true is male, false is female,
                        false,
                        false, // true if user the first sign in
                    )
                )
                add(
                    User(
                        "jennyvandyne@gmail.com",
                        "123456",
                        "jennyvandyne2@gmail.com",
                        null,
                        "https://firebasestorage.googleapis.com/v0/b/hearme-app-16567.appspot.com/o/images%2Fusers%2Fuser_jennyvandyne%40gmail.com.png?alt=media&token=0511621c-5e64-4e2f-97ed-cc22eb5515d9&_gl=1*1bw5udo*_ga*MTUyOTk3NDI1NS4xNjkzMzU5NjY4*_ga_CW55HF8NVT*MTY5NTk2MDMwNi4xMS4xLjE2OTU5NjIwMzEuNjAuMC4w",
                        "Jenny Vandyne",
                        "Van",
                        Date("28/01/2002"),
                        "VN",
                        "32 846 79 25",
                        4567,
                        true, // true is male, false is female,
                        false,
                        false, // true if user the first sign in
                    )
                )
                add(
                    User(
                        "octavioliu@gmail.com",
                        "123456",
                        "octavioliu2@gmail.com",
                        null,
                        "https://firebasestorage.googleapis.com/v0/b/hearme-app-16567.appspot.com/o/images%2Fusers%2Fuser_octavioliu%40gmail.com.png?alt=media&token=3dc9c59d-dbe9-4337-a816-fffb91705386&_gl=1*1m9710p*_ga*MTUyOTk3NDI1NS4xNjkzMzU5NjY4*_ga_CW55HF8NVT*MTY5NTk2MDMwNi4xMS4xLjE2OTU5NjIxMDguNDMuMC4w",
                        "Octavio Liu",
                        "Liu",
                        Date("28/01/2002"),
                        "VN",
                        "32 846 79 25",
                        4567,
                        true, // true is male, false is female,
                        false,
                        false, // true if user the first sign in
                    )
                )
                add(
                    User(
                        "jarrodwood@gmail.com",
                        "123456",
                        "jarrodwood2@gmail.com",
                        null,
                        "https://firebasestorage.googleapis.com/v0/b/hearme-app-16567.appspot.com/o/images%2Fusers%2Fuser_jarrodwood%40gmail.com.png?alt=media&token=61a7629e-dcf9-4e67-8a52-b1cb9a55a704&_gl=1*1ol3wx3*_ga*MTUyOTk3NDI1NS4xNjkzMzU5NjY4*_ga_CW55HF8NVT*MTY5NTk2MDMwNi4xMS4xLjE2OTU5NjE5ODMuNDEuMC4w",
                        "Jarrod Wood",
                        "Wood",
                        Date("28/01/2002"),
                        "VN",
                        "32 846 79 25",
                        4567,
                        true, // true is male, false is female,
                        false,
                        false, // true if user the first sign in
                    )
                )
                add(
                    User(
                        "joaquinhowell@gmail.com",
                        "123456",
                        "joaquinhowell2@gmail.com",
                        null,
                        "https://firebasestorage.googleapis.com/v0/b/hearme-app-16567.appspot.com/o/images%2Fusers%2Fuser_joaquinhowell%40gmail.com.png?alt=media&token=80542bd1-deb9-410d-a0c4-f4a5bb2903fa&_gl=1*1049idl*_ga*MTUyOTk3NDI1NS4xNjkzMzU5NjY4*_ga_CW55HF8NVT*MTY5NTk2MDMwNi4xMS4xLjE2OTU5NjIwNzUuMTYuMC4w",
                        "Joaquin Howell",
                        "Howell",
                        Date("28/01/2002"),
                        "VN",
                        "32 846 79 25",
                        4567,
                        true, // true is male, false is female,
                        false,
                        false, // true if user the first sign in
                    )
                )
                add(
                    User(
                        "guspowell@gmail.com",
                        "1234567",
                        "guspowell2@gmail.com",
                        null,
                        "https://firebasestorage.googleapis.com/v0/b/hearme-app-16567.appspot.com/o/images%2Fusers%2Fuser_guspowell%40gmail.com.png?alt=media&token=8bb86caa-daac-46bc-9256-bc5112eaa7bd&_gl=1*1cnlz40*_ga*MTUyOTk3NDI1NS4xNjkzMzU5NjY4*_ga_CW55HF8NVT*MTY5NTk2MDMwNi4xMS4xLjE2OTU5NjE5NDcuNi4wLjA.",
                        "Gus Powell",
                        "Powell",
                        Date("28/01/2002"),
                        "VN",
                        "32 846 79 25",
                        4567,
                        true, // true is male, false is female,
                        false,
                        false, // true if user the first sign in
                    )
                )
                add(
                    User(
                        "reyesfreeman@gmail.com",
                        "1234567",
                        "reyesfreeman2@gmail.com",
                        null,
                        "https://firebasestorage.googleapis.com/v0/b/hearme-app-16567.appspot.com/o/images%2Fusers%2Fuser_reyesfreeman%40gmail.com.png?alt=media&token=a8aa3760-3cd4-4ca8-aec4-af72f98dc2c0&_gl=1*1i4lnwm*_ga*MTUyOTk3NDI1NS4xNjkzMzU5NjY4*_ga_CW55HF8NVT*MTY5NTk2MDMwNi4xMS4xLjE2OTU5NjIxMzkuMTIuMC4w",
                        "Reyes Freeman",
                        "Freeman",
                        Date("28/01/2002"),
                        "VN",
                        "32 846 79 25",
                        4567,
                        true, // true is male, false is female,
                        false,
                        false, // true if user the first sign in
                    )
                )
                add(
                    User(
                        "raplphho@gmail.com",
                        "123456",
                        "raplphho2@gmail.com",
                        null,
                        "https://firebasestorage.googleapis.com/v0/b/hearme-app-16567.appspot.com/o/images%2Fusers%2Fuser_raplphho%40gmail.com.png?alt=media&token=0caae40b-a9ba-4657-a206-e99c2825b167&_gl=1*15jr6v6*_ga*MTUyOTk3NDI1NS4xNjkzMzU5NjY4*_ga_CW55HF8NVT*MTY5NTk2MDMwNi4xMS4xLjE2OTU5NjIxMjkuMjIuMC4w",
                        "Ralph Ho",
                        "Ho",
                        Date("28/01/2002"),
                        "VN",
                        "32 846 79 25",
                        4567,
                        true, // true is male, false is female,
                        false,
                        false, // true if user the first sign in
                    )
                )
                add(
                    User(
                        "jakemelendez@gmail.com",
                        "123456",
                        "jakemelendez2@gmail.com",
                        null,
                        "https://firebasestorage.googleapis.com/v0/b/hearme-app-16567.appspot.com/o/images%2Fusers%2Fuser_jakemelendez%40gmail.com.png?alt=media&token=afce7826-5e17-40b6-9af0-965fffc49ca4&_gl=1*1jolbjm*_ga*MTUyOTk3NDI1NS4xNjkzMzU5NjY4*_ga_CW55HF8NVT*MTY5NTk2MDMwNi4xMS4xLjE2OTU5NjE5NjQuNjAuMC4w",
                        "Jake Melendez",
                        "Melendez",
                        Date("28/01/2002"),
                        "VN",
                        "32 846 79 25",
                        4567,
                        true, // true is male, false is female,
                        false,
                        false, // true if user the first sign in
                    )
                )
                add(
                    User(
                        "antionedavidson@gmail.com",
                        "123456",
                        "antionedavidson2@gmail.com",
                        null,
                        "https://firebasestorage.googleapis.com/v0/b/hearme-app-16567.appspot.com/o/images%2Fusers%2Fuser_antionedavidson%40gmail.com.png?alt=media&token=a810f67b-deb4-43cc-89b6-a1a7843c6029&_gl=1*1x8icf9*_ga*MTUyOTk3NDI1NS4xNjkzMzU5NjY4*_ga_CW55HF8NVT*MTY5NTk2MDMwNi4xMS4xLjE2OTU5NjE5MjIuMzEuMC4w",
                        "Antione Davidson",
                        "Davidson",
                        Date("28/01/2002"),
                        "VN",
                        "32 846 79 25",
                        4567,
                        true, // true is male, false is female,
                        false,
                        false, // true if user the first sign in
                    )
                )
            }
            return data
        }
    }
}