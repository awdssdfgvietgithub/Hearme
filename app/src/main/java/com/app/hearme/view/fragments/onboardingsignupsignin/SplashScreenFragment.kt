package com.app.hearme.view.fragments.onboardingsignupsignin

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.app.hearme.MainActivity
import com.app.hearme.R
import com.app.hearme.databinding.FragmentSplashScreenBinding

class SplashScreenFragment : Fragment() {
    private lateinit var binding: FragmentSplashScreenBinding
    private lateinit var mainActivity: MainActivity
    private var handler: Handler = Handler()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_splash_screen, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.progressBar.visibility = View.VISIBLE
        mainActivity = activity as MainActivity

        handler.postDelayed({
            binding.progressBar.visibility = View.GONE
            findNavController().navigate(R.id.walkThroughFragment)
            //findNavController().navigate(R.id.signInFragment)

        }, 3000)

        mainActivity.showBottomNav("gone")
        mainActivity.customToolbar("gone")
    }
}