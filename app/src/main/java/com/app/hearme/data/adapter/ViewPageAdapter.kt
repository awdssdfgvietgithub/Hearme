package com.app.hearme.data.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.app.hearme.view.tab_viewpager.TabPodcastFragment
import com.app.hearme.view.tab_viewpager.TabSongFragment
import com.app.hearme.view.fragments.onboardingsignupsignin.SplashScreenFragment

class ViewPageAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return  when (position) {
            0 -> { TabSongFragment() }
            1 -> { TabPodcastFragment() }
            else -> { SplashScreenFragment() }
        }
    }
}