package com.app.hearme.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.hearme.MainActivity
import com.app.hearme.R
import com.app.hearme.data.adapter.ArtistAdapter
import com.app.hearme.data.adapter.CategoryAdapter
import com.app.hearme.data.adapter.MusicAdapter
import com.app.hearme.databinding.FragmentPodcastBinding
import com.app.hearme.model.Artist
import com.app.hearme.model.Category
import com.app.hearme.model.Music
import com.app.hearme.viewmodel.HomeViewModel

class PodcastFragment : Fragment() {
    private var _binding: FragmentPodcastBinding? = null
    private val binding get() = _binding!!
    private lateinit var musicAdapter: MusicAdapter
    private lateinit var artistAdapter: ArtistAdapter
    private lateinit var categoryAdapter: CategoryAdapter

    private val viewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_podcast, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).showBottomNav("VISIBLE")

        (activity as MainActivity).customToolbar(
            "VISIBLE",
            "Podcasts",
            null,
            R.color.transparent,
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back),
            showIcMore = true,
            showIcFilter = true,
            showIcSearch = true
        )

        (activity as MainActivity).binding.toolBar.setNavigationOnClickListener() {
            findNavController().popBackStack()
        }

        viewModel.lstDataMusic.observe((activity as MainActivity), Observer {
            displayRecyclerViewMusic(it as ArrayList<Music>)
            if (it.isEmpty())
                Toast.makeText(context, "list Music is null or empty", Toast.LENGTH_SHORT).show()
        })
        viewModel.getListDataMusic()

        viewModel.lstDataArtist.observe((activity as MainActivity), Observer {
            displayRecyclerViewArtist(it as ArrayList<Artist>)
            if (it.isEmpty())
                Toast.makeText(context, "list Artist is null or empty", Toast.LENGTH_SHORT).show()
        })
        viewModel.getListDataArtist()

        viewModel.lstDataCategory.observe((activity as MainActivity), Observer {
            displayRecyclerViewCategory(it as ArrayList<Category>)
        })
        viewModel.getListDataArtist()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun displayRecyclerViewMusic(lstData: ArrayList<Music>) {
        val layoutRecyclerViewMusic =
            LinearLayoutManager(view?.context, LinearLayoutManager.HORIZONTAL, false)
        musicAdapter = MusicAdapter(lstData, 0,this)
        binding.recyclerViewPopularPodcasts.apply {
            layoutManager = layoutRecyclerViewMusic
            adapter = musicAdapter
        }
    }

    private fun displayRecyclerViewArtist(lstData: ArrayList<Artist>) {
        val layoutRecyclerViewArtist =
            LinearLayoutManager(view?.context, LinearLayoutManager.HORIZONTAL, false)
        artistAdapter = ArtistAdapter(lstData, 0)
        binding.recyclerViewPopularArtists.apply {
            layoutManager = layoutRecyclerViewArtist
            adapter = artistAdapter
        }
    }

    private fun displayRecyclerViewCategory(lstData: ArrayList<Category>) {
        val layoutRecyclerView =
            GridLayoutManager(view?.context, 2, LinearLayoutManager.VERTICAL, false)
        categoryAdapter = CategoryAdapter(lstData, 0)
        binding.recyclerViewCategories.apply {
            layoutManager = layoutRecyclerView
            adapter = categoryAdapter
        }
    }

}