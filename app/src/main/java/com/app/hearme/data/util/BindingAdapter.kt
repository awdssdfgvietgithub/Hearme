package com.app.hearme.data.util

import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.widget.*
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatCheckedTextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.media3.common.MediaItem
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.card.MaterialCardView
import com.google.android.material.imageview.ShapeableImageView
import com.app.hearme.MainActivity
import com.app.hearme.R
import com.app.hearme.data.MusicsData
import com.app.hearme.model.*
import com.app.hearme.view.fragments.search.SongPlayFragment
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*


@BindingAdapter("app:setImage")
fun setImage(imageView: ImageView, id: Int) {
    Picasso.get().load(id)
        .networkPolicy(NetworkPolicy.NO_CACHE)
        .memoryPolicy(MemoryPolicy.NO_CACHE)
        .error(R.drawable.ellipse)
        .into(imageView)
}

@BindingAdapter("app:setImageUrl")
fun setImageUrl(imageView: ImageView, url: String) {
    val options = RequestOptions()
        .centerCrop()
        .error(R.drawable.ellipse)
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        .priority(Priority.LOW)
        .dontTransform()

    Glide.with(imageView.context)
        .load(url)
        .apply(options)
        .transition(DrawableTransitionOptions.withCrossFade(250))
        .into(imageView)
}

@BindingAdapter("app:setImageBase64")
fun setImageBase64(imageView: ImageView, base64: String?) {
    if (base64.isNullOrEmpty()) {
        Glide.with(imageView.context)
            .load(R.drawable.ellipse)
            .into(imageView)
        return
    }

    val imageBytes = android.util.Base64.decode(base64, android.util.Base64.DEFAULT)
    val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
    imageView.setImageBitmap(decodedImage)
}

fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
    val (height: Int, width: Int) = options.outHeight to options.outWidth
    var inSampleSize = 1

    if (height > reqHeight || width > reqWidth) {
        val halfHeight: Int = height / 2
        val halfWidth: Int = width / 2

        while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth) {
            inSampleSize *= 2
        }
    }

    return inSampleSize
}


@BindingAdapter("followersCountText")
fun setFollowersCountText(view: TextView, followersCount: Int?) {
    val text = followersCount?.toString() ?: "0"
    view.text = text
}

@BindingAdapter("app:setDate")
fun setDate(text: TextView, dateInput: Date) {
    val formatter = SimpleDateFormat("dd/MM/yyyy")
    val date = Date()
    val current = formatter.format(date)
    val str = formatter.format(dateInput)
    if (current.compareTo(str) == 0)
        text.text = "Today"
    else text.text = "Yesterday"
}

@BindingAdapter("app:setDuration")
fun setDuration(text: TextView, time: Time) {
    text.text = "${time.minute}:${time.second} mins"
}

@BindingAdapter("app:setCategory")
fun setCategory(text: TextView, check: Boolean) {
    if (check)
        text.text = "Single"
    else
        text.text = "Album"
}

@BindingAdapter("app:setLoveList")
fun setLoveList(checkBox: CheckBox, music: Music) {
    val mainActivity = checkBox.context as MainActivity
    val user =
        mainActivity.viewModelUser.lstDataUser.value?.first { it.email == "phuongviet.huit@gmail.com" }
    mainActivity.viewModelUser.lstDataUser.observe(mainActivity) {
        checkBox.isChecked =
            user?.listMusicsLoved?.none { it.musicID == music.musicID } == false
    }
}

@BindingAdapter("app:clickLoveList")
fun clickLoveList(checkBox: CheckBox, music: Music) {
    val mainActivity = checkBox.context as MainActivity
    checkBox.setOnClickListener() {
        var isLove = false

        if (mainActivity.viewModelUser.isMusicInBlackList(
                "phuongviet.huit@gmail.com",
                music
            )
        ) {
            val builder = AlertDialog.Builder(checkBox.context)
            builder.apply {
                setTitle("Confirm")
                setMessage("${music.musicName} is putting into blacklist, are you want to remove it?")
                setPositiveButton(
                    "YES"
                ) { dialog, _ ->
                    mainActivity.viewModelUser.apply {
                        music.let {
                            updateBlackListMusic(
                                "phuongviet.huit@gmail.com",
                                it,
                                false
                            )
                            updateListMusicsLoved(
                                "phuongviet.huit@gmail.com",
                                it,
                                true
                            )
                        }
                    }
                    mainActivity.showSnack(
                        checkBox,
                        "You added ${music.musicName} to love list!"
                    )
                    dialog.dismiss()
                }
                setNegativeButton("NO") { dialog, _ ->
                    mainActivity.viewModelUser.updateListMusicsLoved(
                        "phuongviet.huit@gmail.com",
                        music,
                        false
                    )
                    dialog.dismiss()
                }
            }.create().show()
        } else {
            if (mainActivity.viewModelUser.lstDataUser.value?.first { it.email == "phuongviet.huit@gmail.com" }?.listMusicsLoved?.none { it.musicID == music.musicID } == true) {
                isLove = true
                mainActivity.showSnack(
                    checkBox,
                    "You added ${music.musicName} to love list!"
                )
            } else {
                isLove = false
                mainActivity.showSnack(
                    checkBox,
                    "You removed ${music.musicName} from love list!"
                )
            }
            mainActivity.viewModelUser.updateListMusicsLoved(
                "phuongviet.huit@gmail.com",
                music,
                isLove
            )
        }
    }
}

@BindingAdapter("app:setPlayList")
fun setPlayList(checkBox: CheckBox, music: Music) {
//    val mainActivity = checkBox.context as MainActivity
//    val user =
//        mainActivity.viewModelUser.lstDataUser.value?.first { it.email == "phuongviet.huit@gmail.com" }
//    mainActivity.viewModelUser.lstDataUser.observe(mainActivity) {
//        checkBox.isChecked =
//            user?.listPlayedMusic?.none { it.musicID == music.musicID } == false
//    }
}

@BindingAdapter("app:clickPlayList")
fun clickPlayList(checkBox: CheckBox, music: Music) {
    val mainActivity = checkBox.context as MainActivity
    checkBox.setOnClickListener() {
        var isLove = false

        if (mainActivity.viewModelUser.isMusicInBlackList(
                "phuongviet.huit@gmail.com",
                music
            )
        ) {
            val builder = AlertDialog.Builder(checkBox.context)
            builder.apply {
                setTitle("Confirm")
                setMessage("${music.musicName} is putting into blacklist, are you want to remove it?")
                setPositiveButton(
                    "YES"
                ) { dialog, _ ->
                    mainActivity.viewModelUser.apply {
                        music.let {
                            updateBlackListMusic(
                                "phuongviet.huit@gmail.com",
                                it,
                                false
                            )
                            updateListPlayedMusic(
                                "phuongviet.huit@gmail.com",
                                it,
                                true
                            )
                        }
                    }
                    mainActivity.showSnack(
                        checkBox,
                        "You added ${music.musicName} to playlist!"
                    )
                    dialog.dismiss()
                }
                setNegativeButton("NO") { dialog, _ ->
                    mainActivity.viewModelUser.updateListPlayedMusic(
                        "phuongviet.huit@gmail.com",
                        music,
                        false
                    )
                    dialog.dismiss()
                }
            }.create().show()
        } else {
//            if (mainActivity.viewModelUser.lstDataUser.value?.first { it.email == "phuongviet.huit@gmail.com" }?.listPlayedMusic?.none { it.musicID == music.musicID } == true) {
//                isLove = true
//                mainActivity.showSnack(
//                    checkBox,
//                    "You added ${music.musicName} to playlist!"
//                )
//            } else {
//                isLove = false
//                mainActivity.showSnack(
//                    checkBox,
//                    "You removed ${music.musicName} from playlist!"
//                )
//            }
//            mainActivity.viewModelUser.updateListPlayedMusic(
//                "phuongviet.huit@gmail.com",
//                music,
//                isLove
//            )
        }
    }
}

@BindingAdapter("app:setDownList")
fun setDownList(checkBox: CheckBox, music: Music) {
    val mainActivity = checkBox.context as MainActivity
    val user =
        mainActivity.viewModelUser.lstDataUser.value?.first { it.email == "phuongviet.huit@gmail.com" }
    mainActivity.viewModelUser.lstDataUser.observe(mainActivity) {
        checkBox.isChecked =
            user?.listMusicsDownloaded?.none { it.musicID == music.musicID } == false
    }
}

@BindingAdapter("app:clickDownList")
fun clickDownList(checkBox: CheckBox, music: Music) {
    val mainActivity = checkBox.context as MainActivity
    checkBox.setOnClickListener() {
        var isDownloaded = false

        if (mainActivity.viewModelUser.isMusicInBlackList(
                "phuongviet.huit@gmail.com",
                music
            )
        ) {
            val builder = AlertDialog.Builder(checkBox.context)
            builder.apply {
                setTitle("Confirm")
                setMessage("${music.musicName} is putting into blacklist, are you want to remove it?")
                setPositiveButton(
                    "YES"
                ) { dialog, _ ->
                    mainActivity.viewModelUser.apply {
                        music.let {
                            updateBlackListMusic(
                                "phuongviet.huit@gmail.com",
                                it,
                                false
                            )
                            updateListMusicsDownloaded(
                                "phuongviet.huit@gmail.com",
                                it,
                                true
                            )
                        }
                    }
                    mainActivity.showSnack(
                        checkBox,
                        "You added ${music.musicName} to downloaded list!"
                    )
                    dialog.dismiss()
                }
                setNegativeButton("NO") { dialog, _ ->
                    mainActivity.viewModelUser.updateListMusicsDownloaded(
                        "phuongviet.huit@gmail.com",
                        music,
                        false
                    )
                    dialog.dismiss()
                }
            }.create().show()
        } else {
            if (mainActivity.viewModelUser.lstDataUser.value?.first { it.email == "phuongviet.huit@gmail.com" }?.listMusicsDownloaded?.none { it.musicID == music.musicID } == true) {
                isDownloaded = true
                mainActivity.showSnack(
                    checkBox,
                    "You added ${music.musicName} to downloaded list!"
                )
            } else {
                isDownloaded = false
                mainActivity.showSnack(
                    checkBox,
                    "You removed ${music.musicName} from downloaded list!"
                )
            }
            mainActivity.viewModelUser.updateListMusicsDownloaded(
                "phuongviet.huit@gmail.com",
                music,
                isDownloaded
            )
        }
    }
}

@BindingAdapter("app:setQueueList")
fun setQueueList(checkBox: CheckBox, music: Music) {
    val mainActivity = checkBox.context as MainActivity
    val user =
        mainActivity.viewModelUser.lstDataUser.value?.first { it.email == "phuongviet.huit@gmail.com" }
    mainActivity.viewModelUser.lstDataUser.observe(mainActivity) {
        checkBox.isChecked =
            user?.listMusicsQueue?.none { it.musicID == music.musicID } == false
    }
}

@BindingAdapter("app:clickQueueList")
fun clickQueueList(checkBox: CheckBox, music: Music) {
    val mainActivity = checkBox.context as MainActivity
    checkBox.setOnClickListener() {
        var isQueued = false

        if (mainActivity.viewModelUser.isMusicInBlackList(
                "phuongviet.huit@gmail.com",
                music
            )
        ) {
            val builder = AlertDialog.Builder(checkBox.context)
            builder.apply {
                setTitle("Confirm")
                setMessage("${music.musicName} is putting into blacklist, are you want to remove it?")
                setPositiveButton(
                    "YES"
                ) { dialog, _ ->
                    mainActivity.viewModelUser.apply {
                        music.let {
                            updateBlackListMusic(
                                "phuongviet.huit@gmail.com",
                                it,
                                false
                            )
                            updateListMusicsQueued(
                                "phuongviet.huit@gmail.com",
                                it,
                                true
                            )
                        }
                    }
                    mainActivity.showSnack(
                        checkBox,
                        "You added ${music.musicName} to queued list!"
                    )
                    dialog.dismiss()
                }
                setNegativeButton("NO") { dialog, _ ->
                    mainActivity.viewModelUser.updateListMusicsQueued(
                        "phuongviet.huit@gmail.com",
                        music,
                        false
                    )
                    dialog.dismiss()
                }
            }.create().show()
        } else {
            if (mainActivity.viewModelUser.lstDataUser.value?.first { it.email == "phuongviet.huit@gmail.com" }?.listMusicsQueue?.none { it.musicID == music.musicID } == true) {
                isQueued = true
                mainActivity.showSnack(
                    checkBox,
                    "You added ${music.musicName} to queued list!"
                )
            } else {
                isQueued = false
                mainActivity.showSnack(
                    checkBox,
                    "You removed ${music.musicName} from queued list!"
                )
            }
            mainActivity.viewModelUser.updateListMusicsQueued(
                "phuongviet.huit@gmail.com",
                music,
                isQueued
            )
        }
    }
}


@BindingAdapter("app:setFollowButton")
fun setFollowButton(toggleButton: ToggleButton, artist: Artist) {
    val mainActivity = toggleButton.context as MainActivity
    val user =
        mainActivity.viewModelUser.lstDataUser.value?.first { it.email == "phuongviet.huit@gmail.com" }

    mainActivity.viewModelUser.lstDataUser.observe(mainActivity) {
        toggleButton.isChecked =
            user?.listArtistsFollowing?.none { it.artistId == artist.artistId } == false
    }
}

@BindingAdapter("app:clickFollowButton")
fun clickFollowButton(toggleButton: ToggleButton, artist: Artist) {
    val mainActivity = toggleButton.context as MainActivity

    toggleButton.setOnClickListener() {
        var isFollowing = false
        if (mainActivity.viewModelUser.lstDataUser.value?.first { it.email == "phuongviet.huit@gmail.com" }?.listArtistsFollowing?.none { it.artistId == artist.artistId } == true) {
            isFollowing = true
            mainActivity.showSnack(
                toggleButton,
                "You following ${artist.artistName}!"
            )
        } else {
            isFollowing = false
            mainActivity.showSnack(
                toggleButton,
                "You unfollow ${artist.artistName}!"
            )
        }
        mainActivity.viewModelUser.updateFollowingArtistList(
            "phuongviet.huit@gmail.com",
            artist,
            isFollowing
        )
    }
}

@BindingAdapter("app:setUserFollowButton")
fun setUserFollowButton(toggleButton: ToggleButton, myFollower: User) {
    val mainActivity = toggleButton.context as MainActivity
    val myUser =
        mainActivity.viewModelUser.lstDataUser.value?.first { it.email == "phuongviet.huit@gmail.com" }

    mainActivity.viewModelUser.lstDataUser.observe(mainActivity) { _ ->
        toggleButton.isChecked =
            myUser?.listUserFollowing?.any { it.email == myFollower.email } == true
    }
}

@BindingAdapter("app:clickUserFollowButton")
fun clickUserFollowButton(toggleButton: ToggleButton, myFollower: User) {
    val mainActivity = toggleButton.context as MainActivity
    val myUser =
        mainActivity.viewModelUser.lstDataUser.value?.first { it.email == "phuongviet.huit@gmail.com" }

    toggleButton.setOnClickListener() {
        var isFollowing = false
        if (mainActivity.viewModelUser.lstDataUser.value?.first { it.email == myUser?.email }?.listUserFollowing?.none { it.email == myFollower.email } == true) {
            isFollowing = true
            mainActivity.showSnack(
                toggleButton,
                "You following ${myFollower.fullName}!"
            )
        } else {
            isFollowing = false
            mainActivity.showSnack(
                toggleButton,
                "You unfollow ${myFollower.fullName}!"
            )
        }
        mainActivity.viewModelUser.updateFollowingList(
            "phuongviet.huit@gmail.com",
            myFollower,
            isFollowing
        )
    }
}


@BindingAdapter("app:setPlay")
fun setPlay(checkBox: CheckBox, music: Music) {
    val mainActivity = checkBox.context as MainActivity

    mainActivity.viewModelUser.lstDataUser.observe(mainActivity) { _ ->
        checkBox.isChecked =
            music.isPlaying == true
    }

//    checkBox.isChecked = music.isPlaying == true

}

@BindingAdapter("app:clickPlayForCheckBox")
fun clickPlayForCheckBox(checkBox: CheckBox, music: Music) {
    Log.v(TAG, "P1: Name: ${music.musicName} - Play: ${music.isPlaying}")
    val mainActivity = checkBox.context as MainActivity
    checkBox.setOnClickListener() { it ->
        var isPlaying = false
        if (mainActivity.viewModelMusic.lstDataMusics.value?.first { it.musicID == music.musicID }?.isPlaying == false) {
            isPlaying = true
            if (mainActivity.exoPlayer.isPlaying)
                mainActivity.exoPlayer.stop()

            mainActivity.exoPlayer.setMediaItem(MediaItem.fromUri(music.path))

            mainActivity.showSnack(
                checkBox,
                "You are playing ${music.musicName}!"
            )
            mainActivity.viewModelUser.updateMusicsHistory("phuongviet.huit@gmail.com", music)
            mainActivity.viewModelUser.updatePlayingDownloaded("phuongviet.huit@gmail.com", music)
            // Chuyễn trang và put bundle ở đây

            it.findNavController()
                .navigate(R.id.songPlayFragment // R.id.action_notificationFragment_to_songPlayFragment
                    , Bundle().apply {
                        putString("musicID", music.musicID)
                        putString("fragment", "Notification")
                    }
                )


        } else {
            isPlaying = false
//            mainActivity.mediaPlayer.pause()
            mainActivity.exoPlayer.pause()
            mainActivity.showSnack(
                checkBox,
                "You stop playing ${music.musicName}!"
            )
        }
        music.isPlaying?.let { it1 ->
            mainActivity.viewModelUser.updatePlaying("phuongviet.huit@gmail.com", music,
                !it1
            )
        }
        mainActivity.viewModelMusic.updatePlaying(
            music,
            isPlaying
        )
        music.let { mainActivity.songPlayViewModel.selectItem(it) }
    }
}

@BindingAdapter("music", "fragment", requireAll = false)
fun ClickPlayForCheckBoxForSongPlayFragmnet(
    checkBox: CheckBox,
    music: Music,
    fragment: Fragment? = null,
) {
    val mainActivity = checkBox.context as MainActivity
    checkBox.setOnClickListener() { it ->
        var isPlaying = false
        if (mainActivity.viewModelMusic.lstDataMusics.value?.first { it.musicID == music.musicID }?.isPlaying == false) {
            isPlaying = true
            if (mainActivity.exoPlayer.isPlaying)
                mainActivity.exoPlayer.stop()
//            mainActivity.mediaPlayer =
//                MediaPlayer.create(mainActivity, mainActivity.dataListSong[music.path!!])
//            mainActivity.exoPlayer.setMediaItem(MediaItem.fromUri(music.path!!))

            mainActivity.exoPlayer.play()

            mainActivity.showSnack(
                checkBox,
                "You are playing ${music.musicName}!"
            )
            mainActivity.viewModelUser.updateMusicsHistory("phuongviet.huit@gmail.com", music)

            // Chuyễn trang và put bundle ở đây

            if (fragment is SongPlayFragment) {

            } else {
                it.findNavController()
                    .navigate(R.id.songPlayFragment // R.id.action_notificationFragment_to_songPlayFragment
                        , Bundle().apply {
                            putString("musicID", music.musicID)
                            putString("fragment", "Notification")
                        }
                    )
            }


        } else {
            isPlaying = false
            mainActivity.exoPlayer.pause()
            mainActivity.showSnack(
                checkBox,
                "You stop playing ${music.musicName}!"
            )
        }
        music.isPlaying?.let { it1 ->
            mainActivity.viewModelUser.updatePlaying("phuongviet.huit@gmail.com", music,
                !it1
            )
        }
        mainActivity.viewModelMusic.updatePlaying(
            music,
            isPlaying
        )
        music.let { mainActivity.songPlayViewModel.selectItem(it) }
    }
}

@BindingAdapter("app:clickPlayForButton")
fun clickPlayForButton(appCompatButton: AppCompatButton, music: Music) {
    val mainActivity = appCompatButton.context as MainActivity

    appCompatButton.setOnClickListener() { it ->
        var isPlaying = false
        if (mainActivity.viewModelMusic.lstDataMusics.value?.first { it.musicID == music.musicID }?.isPlaying == false) {
            isPlaying = true
            if (mainActivity.exoPlayer.isPlaying)
                mainActivity.exoPlayer.stop()

            mainActivity.exoPlayer.setMediaItem(MediaItem.fromUri(music.path))
//            mainActivity.mediaPlayer =
//                MediaPlayer.create(mainActivity, mainActivity.dataListSong[music.path!!])

//            mainActivity.mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)


            mainActivity.showSnack(
                appCompatButton,
                "You are playing ${music.musicName}!"
            )
            mainActivity.viewModelUser.updateMusicsHistory("phuongviet.huit@gmail.com", music)

            // Chuyễn trang và put bundle ở đây

            it.findNavController()
                .navigate(R.id.songPlayFragment // R.id.action_notificationFragment_to_songPlayFragment
                    , Bundle().apply {
                        putString("musicID", music.musicID)
                        putString("fragment", "Notification")
                    }
                )


        } else {
            isPlaying = false
            mainActivity.exoPlayer.pause()
            mainActivity.showSnack(
                appCompatButton,
                "You stop playing ${music.musicName}!"
            )
        }
        mainActivity.viewModelUser.updatePlaying("phuongviet.huit@gmail.com", music, true)
        mainActivity.viewModelMusic.updatePlaying(
            music,
            isPlaying
        )
        music.let { mainActivity.songPlayViewModel.selectItem(it) }
    }
}

@BindingAdapter("app:setTopicSearch")
fun setTopicSearch(toggleButton: ToggleButton, topicSearch: TopicSearch) {
    val mainActivity = toggleButton.context as MainActivity

    mainActivity.viewModelTopicSearch.getTopicSearch().observe(mainActivity) { it ->
        toggleButton.isChecked = it.first { it.name == topicSearch.name }.isChecked
    }
}

@BindingAdapter("app:clickTopicSearch")
fun clickTopicSearch(toggleButton: ToggleButton, topicSearch: TopicSearch) {
    val mainActivity = toggleButton.context as MainActivity

    toggleButton.setOnClickListener() {
        mainActivity.viewModelTopicSearch.updateChecked(topicSearch.name)
    }
}

@BindingAdapter("app:setBackgroundTint")
fun setBackgroundTint(cardView: MaterialCardView, color: Int) {
    cardView.backgroundTintList = ContextCompat.getColorStateList(cardView.context, color)
}

@BindingAdapter("app:topicSearchTextColor")
fun setColorTopicSearchText(checkedTextView: AppCompatCheckedTextView, topicSearch: TopicSearch) {
    if (checkedTextView.isChecked)
        checkedTextView.setTextColor(checkedTextView.resources.getColor(R.color.white, null))
    else
        checkedTextView.setTextColor(
            checkedTextView.resources.getColor(
                R.color.color_bg_button_continue,
                null
            )
        )
}

@BindingAdapter("app:onCheckedChanged")
fun onCheckedChanged(checkedTextView: AppCompatCheckedTextView, topicSearch: TopicSearch) {
    checkedTextView.setOnClickListener() {
        if (checkedTextView.isChecked) {
            checkedTextView.isChecked
            checkedTextView.setTextColor(checkedTextView.resources.getColor(R.color.white, null))
        } else {
            checkedTextView.isChecked = false
            checkedTextView.setTextColor(
                checkedTextView.resources.getColor(
                    R.color.color_bg_button_continue,
                    null
                )
            )
        }
    }
}

@BindingAdapter("app:setTextYear")
fun setTextYear(text: TextView, date: Date) {
    val calendar = Calendar.getInstance()
    calendar.time = date
    text.text = calendar.get(Calendar.YEAR).toString()
}

@BindingAdapter("app:setWidthContainerAlbum")
fun setWidthContainerAlbum(shapeableImageView: ShapeableImageView, music: Music) {
    val displayMetrics = DisplayMetrics()
    val mainBinding = shapeableImageView.context as MainActivity
    mainBinding.windowManager.defaultDisplay.getMetrics(displayMetrics)
    Log.v(
        TAG,
        "Width: ${(displayMetrics.widthPixels / 2) - (24 * 2 + 12)} Height: ${displayMetrics.heightPixels}"
    )

    val params = shapeableImageView.layoutParams
    params.width = (displayMetrics.widthPixels / 2) - (24 * 2 + 25)
    params.height = (displayMetrics.widthPixels / 2) - (24 * 2 + 25)
    shapeableImageView.layoutParams = params
}

@BindingAdapter("app:changeFormatNumber")
fun changeFormatNumber(textView: TextView, number: Int) {
    val formatter: NumberFormat = DecimalFormat("#,###")
    val changedNumber = formatter.format(number)
    textView.text = "$changedNumber monthly listeners"
}

@BindingAdapter("app:setTextAlbums")
fun setTextAlbums(textView: TextView, playlist: Playlist) {
    textView.text = "${playlist.artist?.artistName}  |  ${playlist.release}"
}

@BindingAdapter("app:setTextSongs")
fun setTextSongs(textView: TextView, music: Music) {
    textView.text =
        "${music.artist.artistName}  |  ${music.duration.minute}:${music.duration.second} mins"
}

@BindingAdapter("app:setNumberOfFollowers")
fun setNumberOfFollowers(textView: TextView, arrayList: ArrayList<User>) {
    val mainActivity = textView.context as MainActivity
    mainActivity.viewModelUser.lstDataUser.observe(mainActivity) {
        textView.text = arrayList.size.toString()
    }
}

@BindingAdapter("app:setNumberOfUserFollowing")
fun setNumberOfUserFollowing(textView: TextView, arrayList: ArrayList<User>) {
    val mainActivity = textView.context as MainActivity
    mainActivity.viewModelUser.lstDataUser.observe(mainActivity) {
        textView.text = arrayList.size.toString()
    }
}

@BindingAdapter("app:setTextArtist")
fun setTextArtist(textView: TextView, artist: Artist) {
    val lstMusicsData = MusicsData.dataMusic()
    val lst = lstMusicsData.filter { it.artist.artistId == artist.artistId }
    if (artist.isSinger)
        textView.text = "${lst.size} Songs"
    else textView.text = "${lst.size} Episodes"
}

@BindingAdapter("app:setTextNameArtist")
fun setTextNameArtist(textView: TextView, name: String) {
    val mainActivity = textView.context as MainActivity
    if (mainActivity.isLandscape())
        textView.text = name
    else {
        var n = ""
        name.forEach {
            n += it
            if (n.length >= 7) {
                textView.text = "$n..."
                return
            }
        }
        textView.text = name
    }
}