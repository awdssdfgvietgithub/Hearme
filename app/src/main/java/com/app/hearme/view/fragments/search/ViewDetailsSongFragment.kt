package com.app.hearme.view.fragments.search

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.hearme.MainActivity
import com.app.hearme.R
import com.app.hearme.data.adapter.MusicAdapter
import com.app.hearme.databinding.FragmentViewDetailsSongBinding
import com.app.hearme.model.Music
import com.app.hearme.model.User
import com.app.hearme.viewmodel.MusicViewModel
import com.app.hearme.viewmodel.UserViewModel

class ViewDetailsSongFragment : Fragment() {
    private lateinit var binding: FragmentViewDetailsSongBinding
    private lateinit var mainActivity: MainActivity
    private val musicViewModel: MusicViewModel by activityViewModels()
    private val userViewModel: UserViewModel by activityViewModels()

    private lateinit var musicID: String
    private var music: Music? = null
    private var musicsMoreLikeThis: ArrayList<Music>? = arrayListOf()
    private var user: User? = null

    private lateinit var musicAdapter: MusicAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_view_details_song, container, false
        )
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        mainActivity = activity as MainActivity
        musicID = arguments?.getString("musicID").toString()
        music = musicViewModel.lstDataMusics.value?.first { it.musicID == musicID }
        musicsMoreLikeThis =
            musicViewModel.lstDataMusics.value?.filter { it.category.categoryID == music?.category?.categoryID && it.musicID != musicID } as ArrayList<Music>?
        user = userViewModel.lstDataUser.value?.first { it.email == mainActivity.email }
        binding.music = music

        binding.textViewCategory.text = if (music?.isAlbum == true) "Album" else "Song"
        val duration = "${music?.duration?.minute}:${music?.duration?.second}"
        binding.textViewDuration.text = duration

        Handler(Looper.getMainLooper()).postDelayed({
            music?.let {
                mainActivity.initSpinnerMore(binding.spinnerDropDownMore,
                    it,
                    1,
                    this)
            }
        }, 200)
        displayRecyclerViewMoreLikeThis()

        mainActivity.showBottomNav("GONE")
        mainActivity.customToolbar(
            "VISIBLE",
            navIcon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back),
            showIcMore = true
        )
        mainActivity.binding.toolBar.setNavigationOnClickListener() {
            findNavController().popBackStack()
        }

        binding.btnPlay.setOnClickListener {
            findNavController().navigate(R.id.songPlayFragment)
        }

        binding.imageArtist.setOnClickListener() {
            findNavController().navigate(R.id.fullImageFragment, Bundle().apply {
                putString("url", music?.image)
            })
        }
    }

    private fun displayRecyclerViewMoreLikeThis() {
        musicAdapter = musicsMoreLikeThis?.let { MusicAdapter(it, 6, this) }!!
        binding.recyclerViewMusicMoreLikeThis.apply {
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
            adapter = musicAdapter
        }
    }
}