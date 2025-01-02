package com.app.hearme.view.fragments.search

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.hearme.MainActivity
import com.app.hearme.R
import com.app.hearme.data.adapter.MusicAdapter
import com.app.hearme.databinding.FragmentViewDetailsAlbumBinding
import com.app.hearme.model.Music
import com.app.hearme.viewmodel.MusicViewModel
import com.app.hearme.viewmodel.UserViewModel


class ViewDetailsAlbumFragment : Fragment() {
    private lateinit var binding: FragmentViewDetailsAlbumBinding
    private lateinit var mainActivity: MainActivity

    private val musicViewModel: MusicViewModel by activityViewModels()
    private val userViewModel: UserViewModel by activityViewModels()

    private lateinit var musicID: String
    private lateinit var music: Music
    private lateinit var email: String

    private lateinit var musicAdapter: MusicAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_view_details_album,
            container,
            false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        mainActivity = activity as MainActivity
        musicID = arguments?.getString("musicID").toString()
        email = "phuongviet.huit@gmail.com"
        music = musicViewModel.lstDataMusics.value?.first { it.musicID == musicID }!!
        binding.music = music

        displayRecyclerView()
        Handler(Looper.getMainLooper()).postDelayed({
            mainActivity.initSpinnerMore(binding.spinnerDropDownMore,
                music,
                1,
                this)
        }, 200)

        mainActivity.showBottomNav("GONE")
        mainActivity.customToolbar(
            "VISIBLE",
            navIcon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back),
            showIcMore = true
        )
        mainActivity.binding.toolBar.setNavigationOnClickListener() {
            findNavController().popBackStack()
        }

        binding.imageArtist.setOnClickListener() {
            findNavController().navigate(R.id.fullImageFragment, Bundle().apply {
                putString("url", music.image)
            })
        }
    }

    private fun displayRecyclerView() {
        musicAdapter =
            MusicAdapter(musicViewModel.lstDataMusics.value?.filter { it.isAlbum && it.musicID != musicID } as ArrayList<Music>,
                6,
                this)
        binding.recyclerViewSongs.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = musicAdapter
        }
    }

//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        outState.putString("musicID", musicID)
//    }
}