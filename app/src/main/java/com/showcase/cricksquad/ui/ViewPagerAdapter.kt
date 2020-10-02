package com.showcase.cricksquad.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.showcase.cricksquad.Team

class ViewPagerAdapter(
    fragmentActivity: FragmentActivity,
    private val dataSet: List<Team>
) : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return SquadFragment.getInstance(dataSet[position].players)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}