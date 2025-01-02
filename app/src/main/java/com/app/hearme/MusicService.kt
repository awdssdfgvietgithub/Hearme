package com.app.hearme

import android.app.Service
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Binder
import android.os.IBinder
import android.support.v4.media.session.MediaSessionCompat
import androidx.core.app.NotificationCompat
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.app.hearme.model.Music
import com.app.hearme.R
import java.io.IOException
import java.net.URL


class MusicService: Service() {//MediaSessionService() {

    private  var myBinder = MyBinder()
    private lateinit var mediaSession: MediaSessionCompat
    override fun onBind(p0: Intent?): IBinder {
        mediaSession = MediaSessionCompat(baseContext, "My Music")
        return myBinder
    }

    inner class MyBinder: Binder() {
        fun currentService(): MusicService {
            return this@MusicService
        }
    }

    fun showNotification(music: Music) {
        val options = RequestOptions()
            .centerCrop()
            .error(R.drawable.ellipse)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .priority(Priority.HIGH)
            .dontTransform()

        var image: Bitmap ?= null
        image = try {
            val url = URL(music.image)
            BitmapFactory.decodeStream(url.openConnection().getInputStream())
        } catch (e: IOException) {
            BitmapFactory.decodeResource(resources, R.drawable.img_music)
        }

        val notification = NotificationCompat.Builder(baseContext, MusicApplication.CHANNEL_ID)
            .setContentTitle(music.musicName)
            .setContentText(music.artist.artistName)
            .setSmallIcon(R.drawable.logo_nav)
            .setLargeIcon(image) //music.image))
            .setStyle(androidx.media.app.NotificationCompat.MediaStyle().setMediaSession(mediaSession.sessionToken))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setOnlyAlertOnce(true)
            .addAction(R.drawable.ic_previous, "Previous", null)
            .addAction(R.drawable.ic_pause, "Pause", null)
            .addAction(R.drawable.ic_next, "Next", null)
            .build()
        startForeground(13, notification)
    }

//    private var mediaSession: MediaSession? = null
//    private lateinit var mainActivity: MainActivity
//    override fun onGetSession(controllerInfo: MediaSession.ControllerInfo): MediaSession? =
//        mediaSession
//
//    override fun onCreate() {
//        super.onCreate()
//        mediaSession = MediaSession.Builder(this, mainActivity.exoPlayer).build()
//    }
//
//    override fun onDestroy() {
//        mediaSession?.run {
//            player.release()
//            release()
//            mediaSession = null
//        }
//        super.onDestroy()
//    }


}