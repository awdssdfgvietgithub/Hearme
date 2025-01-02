package com.app.hearme.view.fragments.library

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.hearme.MainActivity
import com.app.hearme.R
import com.app.hearme.data.adapter.MusicAdapter
import com.app.hearme.databinding.FragmentPlaylistDetailBinding
import com.app.hearme.model.Music
import com.app.hearme.model.Playlist
import com.app.hearme.viewmodel.UserViewModel

class PlaylistDetailFragment : Fragment() {
    private var _binding: FragmentPlaylistDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainActivity: MainActivity
    private lateinit var musicAdapter: MusicAdapter
    private var lst = ArrayList<Playlist>()
    private var lstMusic = ArrayList<Music>()
    private var idPlaylist = ""

    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_playlist_detail, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        mainActivity = (activity as MainActivity)

        mainActivity.customToolbar(
            "VISIBLE",
            null,
            null,
            R.color.transparent,
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back),
            showIcMore = false,
            showIcFilter = false,
            showIcSearch = true
        )

        mainActivity.binding.toolBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        binding.imgAvatar.background =
            arguments?.getInt("img")?.let { ContextCompat.getDrawable(requireContext(), it) }
        binding.tvTitle.text = arguments?.getString("name")
        binding.tvNumber.text = arguments?.getString("size")
        idPlaylist = arguments?.getString("id").toString()

        userViewModel.lstDataPlaylist.observe((activity as MainActivity), Observer { playlists ->
            lst = playlists as ArrayList<Playlist>

            lstMusic = lst.find { it.playlistID == idPlaylist }!!.listMusic
            displayRecyclerView(lstMusic)
        })

        binding.btnShuffle.setOnClickListener {
            lstMusic.shuffle()
            findNavController().navigate(R.id.songPlayFragment,
                Bundle().apply {
                    putString("musicID", lstMusic[0].musicID)
                }
            )

        }


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun displayRecyclerView(lstData: ArrayList<Music>) {
        val layoutRecyclerViewMusic =
            LinearLayoutManager(view?.context, LinearLayoutManager.VERTICAL, false)
        musicAdapter = MusicAdapter(lstData, 1,this)
        binding.recyclerView.apply {
            layoutManager = layoutRecyclerViewMusic
            adapter = musicAdapter
        }
    }
}