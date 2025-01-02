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
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.hearme.MainActivity
import com.app.hearme.R
import com.app.hearme.data.adapter.MusicAdapter
import com.app.hearme.databinding.FragmentViewDetailsArtistBinding
import com.app.hearme.model.Artist
import com.app.hearme.model.Music
import com.app.hearme.viewmodel.ArtistViewModel
import com.app.hearme.viewmodel.MusicViewModel

class ViewDetailsArtistFragment : Fragment() {
    private lateinit var binding: FragmentViewDetailsArtistBinding
    private lateinit var mainActivity: MainActivity
    private val artistViewModel: ArtistViewModel by activityViewModels()
    private val musicViewModel: MusicViewModel by activityViewModels()

    private lateinit var artistID: String
    private var artist: Artist? = null
    private var dataSongs: ArrayList<Music> = ArrayList()

    private lateinit var musicAdapter: MusicAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_view_details_artist, container, false
        )
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        mainActivity = activity as MainActivity
        artistID = arguments?.getString("artistID").toString()
        artist = artistViewModel.lstDataArtists.value?.first { it.artistId == artistID }!!
        dataSongs =
            musicViewModel.lstDataMusics.value?.filter { it.artist.artistId == artistID && !it.isAlbum } as ArrayList<Music>
        binding.artist = artist

        displayRecyclerViewPopularSongs()

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
                putString("url", artist?.image)
            })
        }
    }

    private fun displayRecyclerViewPopularSongs() {
        musicAdapter = MusicAdapter(dataSongs,6,this)
        binding.recyclerViewPopularSongs.apply {
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
            adapter = musicAdapter
        }
    }
}