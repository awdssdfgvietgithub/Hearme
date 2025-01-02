package com.app.hearme.view.fragments.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.app.hearme.MainActivity
import com.app.hearme.R
import com.app.hearme.databinding.FragmentViewDetailsPodcastBinding
import com.app.hearme.model.Artist
import com.app.hearme.model.Music
import com.app.hearme.viewmodel.MusicViewModel
import com.app.hearme.viewmodel.UserViewModel

class ViewDetailsPodcastFragment : Fragment() {
    private lateinit var binding: FragmentViewDetailsPodcastBinding
    private lateinit var mainActivity: MainActivity
    private val musicViewModel: MusicViewModel by activityViewModels()
    private val userViewModel: UserViewModel by activityViewModels()

    private lateinit var musicID: String
    private var artist: Artist? = null
    private var music: Music? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_view_details_podcast, container, false
        )
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        mainActivity = activity as MainActivity
        musicID = arguments?.getString("musicID").toString()
        artist = musicViewModel.lstDataMusics.value?.first { it.musicID == musicID }?.artist
        music = musicViewModel.lstDataMusics.value?.first { it.musicID == musicID }
        binding.music = music
        binding.artist = artist

        mainActivity.showBottomNav("GONE")
        mainActivity.customToolbar(
            "VISIBLE",
            navIcon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back),
            showIcMore = true
        )
        mainActivity.binding.toolBar.setNavigationOnClickListener() {
            findNavController().popBackStack()
        }

        binding.image.setOnClickListener() {
            findNavController().navigate(R.id.fullImageFragment, Bundle().apply {
                putString("url", music?.image)
            })
        }
    }
}