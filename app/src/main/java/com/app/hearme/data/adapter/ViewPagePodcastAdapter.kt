package com.app.hearme.data.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.app.hearme.view.fragments.onboardingsignupsignin.SplashScreenFragment
import com.app.hearme.view.tab_viewpager.TabDownloadedFragment
import com.app.hearme.view.tab_viewpager.TabQueueFragment
import com.app.hearme.view.tab_viewpager.TabYourLikesFragment

class ViewPagePodcastAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return  when (position) {
            0 -> { TabYourLikesFragment() }
            1 -> { TabQueueFragment() }
            2 -> { TabDownloadedFragment() }
            else -> { SplashScreenFragment() }
        }
    }
}