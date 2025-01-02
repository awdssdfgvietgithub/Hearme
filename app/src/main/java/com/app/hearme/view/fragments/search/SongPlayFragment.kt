package com.app.hearme.view.fragments.search

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.ContentValues.TAG
import android.content.Context.BIND_AUTO_CREATE
import android.content.Intent
import android.content.ServiceConnection
import android.media.AudioAttributes
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.app.hearme.MainActivity
import com.app.hearme.MusicService
import com.app.hearme.R
import com.app.hearme.databinding.FragmentSongPlayBinding
import com.app.hearme.model.Music
import com.app.hearme.viewmodel.MusicViewModel
import com.app.hearme.viewmodel.SongPlayViewModel
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.concurrent.TimeUnit


class SongPlayFragment : Fragment(), ServiceConnection {
    lateinit var binding: FragmentSongPlayBinding
    private lateinit var mainActivity: MainActivity
    private lateinit var runnable: Runnable
    private var lstDataMusic = ArrayList<Music>()
    lateinit var musicID: String
    private var handler = Handler()
//    private lateinit var exoPlayer: ExoPlayer

    //    private var mediaPlayer = MediaPlayer()
    private var positionSong = 1 // vi tri bai hat da chon

    //    private var lstData = ArrayList<Int>()
    private var music: Music? = null
    private var musicService: MusicService? = null

    private val songPlayViewModel: SongPlayViewModel by activityViewModels()
    private val musicViewModel: MusicViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_song_play, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainActivity = (activity as MainActivity)
        mainActivity.binding.fragmentBottomPlayer.isVisible = false

//         For Starting Service
        val intent = Intent(mainActivity, MusicService::class.java)
        activity?.bindService(intent, this, BIND_AUTO_CREATE)
        activity?.startService(intent)

        musicID = arguments?.getString("musicID").toString()
        music = musicViewModel.lstDataMusics.value?.first { it.musicID == musicID }
        positionSong = lstDataMusic.indexOf(music)
        musicViewModel.lstDataMusics.observe(viewLifecycleOwner, Observer {
            lstDataMusic = it
        })

        setImageMusic(music!!.image)
        binding.tvTitle.text = music?.musicName
        binding.tvDetail.text = music?.artist?.artistName
        binding.btnPlay.isChecked = music?.isPlaying == true

        mainActivity.mediaPlayer.setAudioAttributes(
            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .build()
        )

        binding.music = music
    }

    private fun setImageMusic(strUrl: String) {
        val options = RequestOptions()
            .centerCrop()
            .error(R.drawable.ellipse)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .priority(Priority.HIGH)
            .dontTransform()

        Glide.with(this)
            .load(strUrl)
            .apply(options)
            .transition(DrawableTransitionOptions.withCrossFade(250))
            .into(binding.imgAvatar)
    }

    private fun setResourcesWithMedia3() {
//        mainActivity.exoPlayer = ExoPlayer.Builder(requireContext()).build()
        mainActivity.exoPlayer.addListener(object: Player.Listener {
            override fun onIsPlayingChanged(isPlaying: Boolean) {
                val duration = mainActivity.exoPlayer.duration.toInt()/1000
                binding.seekBar.max = duration
                binding.tvTimeEnd.text = getTimeConvertToString(duration)
            }

            override fun onPositionDiscontinuity(
                oldPosition: Player.PositionInfo,
                newPosition: Player.PositionInfo,
                reason: Int
            ) {
                val currentPosition = mainActivity.exoPlayer.currentPosition.toInt()/1000
                binding.seekBar.progress = currentPosition
                binding.tvTimeEnd.text = getTimeConvertToString(currentPosition)
            }
        })

//        val mediaItem = MediaItem.fromUri("https://cdn.pixabay.com/audio/2023/07/03/audio_80cd47077b.mp3")
//        exoPlayer.setMediaItem(mediaItem)
        mainActivity.exoPlayer.prepare()
        mainActivity.exoPlayer.play()

        binding.seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mainActivity.exoPlayer.seekTo(progress.toLong() * 1000)
                    binding.tvTimeStart.text = getTimeConvertToString(progress)
                    if (progress == mainActivity.exoPlayer.duration.toInt()/1000) {
                        binding.btnPlay.isChecked = false
                        music?.isPlaying = false
                    }
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        val handler = Handler(Looper.getMainLooper())
        handler.post(object: Runnable{
            override fun run() {
                val currentPosition = mainActivity.exoPlayer.currentPosition.toInt()/1000
                binding.seekBar.progress = currentPosition
                binding.tvTimeStart.text = getTimeConvertToString(currentPosition)
                handler.postDelayed(this, 1000)
            }
        })

    }

    fun getTimeConvertToString(duration: Int): String {
        val min = duration / 60
        val sec = duration % 60
        return String.format("%02d:%02d", min, sec)
    }
    override fun onResume() {
        super.onResume()
        binding.fragment = this

        mainActivity.customToolbar(
            "VISIBLE",
            "",
            null,
            R.color.transparent,
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back), true
        )
        mainActivity.showBottomNav("GONE")
        mainActivity.binding.toolBar.setNavigationOnClickListener {
            binding.music = music
            mainActivity.binding.fragmentBottomPlayer.isVisible = true
            music?.let { songPlayViewModel.selectItem(it) }
            findNavController().popBackStack()
        }

        binding.tvLyrics.movementMethod = ScrollingMovementMethod()

//        setResourcesWithMusic()

        setResourcesWithMedia3()

        binding.linearCache.setOnClickListener {
            binding.linearDetail.isVisible = false
            binding.linearCache.isVisible = false
            binding.linearLyrics.isVisible = true
            var fileInputStream: FileInputStream ?= null
            fileInputStream = mainActivity.openFileInput("lyrics.txt")
            val inputStreamReader = InputStreamReader(fileInputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            val stringBuilder = StringBuilder()
            var text: String ?= null
            while (run {
                    text = bufferedReader.readLine()
                    text
                } != null) {
                stringBuilder.append(text)
            }

            binding.tvLyrics.text = stringBuilder.toString()
        }

        binding.btnNext.setOnClickListener {
            if (positionSong == lstDataMusic.size - 1)
                return@setOnClickListener
            positionSong += 1
            music?.isPlaying = false
            music = musicViewModel.lstDataMusics.value?.first { it.musicID == lstDataMusic[positionSong].musicID }
            music?.isPlaying = true
            music?.image?.let { it1 -> setImageMusic(it1) }
            binding.tvTitle.text = music?.musicName
            binding.tvDetail.text = music?.artist?.artistName
            mainActivity.exoPlayer.setMediaItem(MediaItem.fromUri(music?.path!!))
        }

        binding.btnPrevious.setOnClickListener {
            if (positionSong == 0)
                return@setOnClickListener
            positionSong -= 1
            music?.isPlaying = false
            music = musicViewModel.lstDataMusics.value?.first { it.musicID == lstDataMusic[positionSong].musicID }
            music?.isPlaying = true
            music?.image?.let { it1 -> setImageMusic(it1) }
            binding.tvTitle.text = music?.musicName
            binding.tvDetail.text = music?.artist?.artistName
            mainActivity.exoPlayer.setMediaItem(MediaItem.fromUri(music?.path!!))
        }

        binding.btnForward.setOnClickListener {
            if (mainActivity.exoPlayer.isPlaying)
                mainActivity.exoPlayer.seekTo(mainActivity.exoPlayer.currentPosition + 10000)
        }

        binding.btnBackward.setOnClickListener {
            if (mainActivity.exoPlayer.isPlaying)
                mainActivity.exoPlayer.seekTo(mainActivity.exoPlayer.currentPosition - 10000)
        }
    }

    @SuppressLint("DefaultLocale")
    fun convertToMMSS(duration: Int): String {
        val millis = duration.toLong()
        return java.lang.String.format(
            "%02d:%02d",
            TimeUnit.MILLISECONDS.toMinutes(millis) % TimeUnit.HOURS.toMinutes(1),
            TimeUnit.MILLISECONDS.toSeconds(millis) % TimeUnit.MINUTES.toSeconds(1)
        )
    }

    private fun setResourcesWithMusic() {
//        showNotificationMedia(requireContext())
//        if (music != null)
//            mainActivity.showNotificationMedia(music!!) src="https://cdn.pixabay.com/audio/2023/09/25/audio_b475db93de.mp3"

//        mediaPlayer = MediaPlayer.create(context, lstData[positionSong]) // get Song in positionSong
//        mediaPlayer.setDataSource(requireContext(), Uri.parse("https://cdn.pixabay.com/audio/2023/09/25/audio_b475db93de.mp3"))


        binding.seekBar.progress = 0
        binding.seekBar.max = mainActivity.mediaPlayer.duration

        binding.tvTimeEnd.text = convertToMMSS(mainActivity.mediaPlayer.duration)

        if (music?.isPlaying == true)
            mainActivity.mediaPlayer.start()

    }

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        val binder = service as MusicService.MyBinder
        musicService = binder.currentService()
        musicService?.showNotification(music!!)
        Log.v(TAG, "onServiceConnected")
    }

    override fun onServiceDisconnected(p0: ComponentName?) {

    }
}