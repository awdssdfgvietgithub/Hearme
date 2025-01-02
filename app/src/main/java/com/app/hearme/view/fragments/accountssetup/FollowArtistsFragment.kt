package com.app.hearme.view.fragments.accountssetup

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
import com.app.hearme.data.adapter.ArtistAdapter
import com.app.hearme.databinding.FragmentFollowArtistsBinding
import com.app.hearme.model.Artist
import com.app.hearme.viewmodel.ArtistViewModel
import java.util.*

class FollowArtistsFragment : Fragment() {
    private lateinit var binding: FragmentFollowArtistsBinding
    private lateinit var mainActivity: MainActivity

    private lateinit var artistAdapter: ArtistAdapter

    private val artistViewModel: ArtistViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_follow_artists,
            container,
            false
        )
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        mainActivity = (activity as MainActivity)

        artistViewModel.lstDataArtists.observe(viewLifecycleOwner, displayRecyclerView)

        val direction =
            FollowArtistsFragmentDirections.actionFollowArtistsFragmentToNavigationHome()
        binding.btnSkip.setOnClickListener() {
            findNavController().navigate(direction)
        }
        binding.btnContinue.setOnClickListener() {
            findNavController().navigate(direction)
        }

        mainActivity.customToolbar(
            "VISIBLE", "Set Your Fingerprint", null, R.color.transparent,
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back)
        )
        mainActivity.binding.toolBar.setNavigationOnClickListener() {
            findNavController().popBackStack()
        }
    }

    private var displayRecyclerView: Observer<ArrayList<Artist>?> =
        Observer<ArrayList<Artist>?> { artistArrayList ->
            val layout = LinearLayoutManager(view?.context, LinearLayoutManager.VERTICAL, false)
            artistAdapter = artistArrayList?.let { ArtistAdapter(it, 4, this) }!!
            binding.recyclerView.apply {
                layoutManager = layout
                adapter = artistAdapter
            }
        }
}
