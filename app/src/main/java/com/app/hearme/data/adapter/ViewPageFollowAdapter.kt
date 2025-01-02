package com.app.hearme.data.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.app.hearme.view.tab_viewpager.TabFollowFragment

class ViewPageFollowAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle, var email: String): FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return  when (position) {
            0 -> { TabFollowFragment(0, email) }
            1 -> { TabFollowFragment(1, email) }
            else -> { TabFollowFragment(0, email) }
        }
    }
}