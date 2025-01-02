package com.app.hearme.view.fragments.homeactionmenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.hearme.MainActivity
import com.app.hearme.R
import com.app.hearme.data.adapter.ArtistAdapter
import com.app.hearme.databinding.FragmentPopularArtistsBinding
import com.app.hearme.model.Artist
import com.app.hearme.viewmodel.ArtistViewModel

class PopularArtistsFragment : Fragment() {
    private var _binding: FragmentPopularArtistsBinding? = null
    private val binding get() = _binding!!
    private lateinit var artistAdapter: ArtistAdapter

    private val artistViewModel: ArtistViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_popular_artists, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).customToolbar(
            "VISIBLE",
            "Popular Artists",
            null,
            R.color.transparent,
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back),
            showIcMore = false,
            showIcFilter = false,
            showIcSearch = true
        )

        (activity as MainActivity).binding.toolBar.setNavigationOnClickListener() {
            findNavController().popBackStack()
        }

        artistViewModel.lstDataArtists.observe((activity as MainActivity), Observer {
            displayRecyclerView(it.sortedByDescending { it.totalNumberOfListeners }
                .take(5) as ArrayList<Artist>)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun displayRecyclerView(lstData: ArrayList<Artist>) {
        val layoutRecyclerView =
            GridLayoutManager(view?.context, 2, LinearLayoutManager.VERTICAL, false)
        artistAdapter = ArtistAdapter(lstData, 0, this)
        binding.recyclerView.apply {
            layoutManager = layoutRecyclerView
            adapter = artistAdapter
        }
    }
}