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
import com.app.hearme.data.adapter.MusicAdapter
import com.app.hearme.databinding.FragmentTrendingNowBinding
import com.app.hearme.model.Music
import com.app.hearme.viewmodel.MusicViewModel

class TrendingNowFragment : Fragment() {
    private lateinit var binding: FragmentTrendingNowBinding
    private lateinit var musicAdapter: MusicAdapter
    private val viewModelMusic: MusicViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_trending_now, container, false)
        return binding.root
    }

    private fun displayRecyclerView(lstData: ArrayList<Music>) {
        val layoutRecyclerView =
            GridLayoutManager(view?.context, 2, LinearLayoutManager.VERTICAL, false)
        musicAdapter = MusicAdapter(lstData, 0,this)
        binding.recyclerView.apply {
            layoutManager = layoutRecyclerView
            adapter = musicAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        viewModelMusic.lstDataMusics.observe((activity as MainActivity), Observer { list ->
            displayRecyclerView(list.sortedByDescending { it.totalListeners }
                .take(5) as ArrayList<Music>)
        })

        (activity as MainActivity).customToolbar(
            "VISIBLE",
            "Trending Now",
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
    }
}