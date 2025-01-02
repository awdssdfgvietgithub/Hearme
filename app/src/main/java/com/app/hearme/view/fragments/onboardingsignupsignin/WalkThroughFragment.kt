package com.app.hearme.view.fragments.onboardingsignupsignin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.app.hearme.MainActivity
import com.app.hearme.R
import com.app.hearme.databinding.FragmentWalkThroughBinding

class WalkThroughFragment : Fragment() {

    private var _binding: FragmentWalkThroughBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_walk_through, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        mainActivity = activity as MainActivity
        binding.btnGetStarted.setOnClickListener {
            findNavController().navigate(R.id.action_walkThroughFragment_to_letYouInFragment)
//            findNavController().navigate(R.id.navigation_home)
        }

        mainActivity.showBottomNav("gone")
        mainActivity.customToolbar("gone")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}