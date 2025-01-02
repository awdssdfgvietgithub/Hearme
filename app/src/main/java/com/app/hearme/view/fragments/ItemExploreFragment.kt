package com.app.hearme.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.app.hearme.MainActivity
import com.app.hearme.R
import com.app.hearme.databinding.FragmentItemExploreBinding

class ItemExploreFragment : Fragment() {
    private var _binding: FragmentItemExploreBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_item_explore, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val title = arguments?.getString("name")
        (activity as MainActivity).customToolbar(
            "VISIBLE",
            title,
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

        binding.tvWeeklyAlbum.text = "Weekly Album $title"
        binding.tvWeeklySong.text = "Weekly Song $title"
        binding.tvDailyViral.text = "Daily Viral $title"

        binding.cardViewTopAlbumGlobal.setOnClickListener {
            sendData(
                0,
                binding.tvInCardTopAlbumGlobal.text.toString(),
                binding.tvTopAlbumGlobal.text.toString(),
                "The most played albums from last week.The most played albums from last week.The most played albums from last week."
            )
        }

        binding.cardViewTopAlbum.setOnClickListener {
            sendData(
                1,
                binding.tvInCardTopAlbum.text.toString(),
                binding.tvTopAlbum.text.toString(),
                "The most played albums from last week."
            )
        }

        binding.cardViewTopSongGlobal.setOnClickListener {
            sendData(
                2,
                binding.tvInCardTopSongGlobal.text.toString(),
                binding.tvTopSongGlobal.text.toString(),
                "The most played songs from last week."
            )
        }

        binding.cardViewTopSong.setOnClickListener {
            sendData(
                3,
                binding.tvInCardTopSong.text.toString(),
                binding.tvTopSong.text.toString(),
                "The most played songs from last week."
            )
        }

        binding.cardViewTop50Global.setOnClickListener {
            sendData(
                4,
                binding.tvInCardTop50Global.text.toString(),
                binding.tvTop50Global.text.toString(),
                "The list viral top 50 from last week."
            )
        }

        binding.cardViewTop50.setOnClickListener {
            sendData(
                5,
                binding.tvInCardTop50.text.toString(),
                binding.tvTop50.text.toString(),
                "The list viral top 50 from last week."
            )
        }
    }

    private fun sendData(idCard: Int, tvInCard: String, tvTitle: String, tvDetail: String) {
        val bundle = Bundle().apply {
            putInt("idCard", idCard)
            putString("tvInCard", tvInCard)
            putString("tvTitle", tvTitle)
            putString("tvDetail", tvDetail)
        }
        this.arguments = bundle

        findNavController().navigate(R.id.topItemFragment, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}