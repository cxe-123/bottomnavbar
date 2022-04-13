package com.example.worklah.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.worklah.remainedFile.JobToStartFragment
import com.example.worklah.remainedFile.JobToEndFragment
import com.example.worklah.remainedFile.JobCancelledFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> {
                JobToStartFragment()
            }
            1 -> {
                JobToEndFragment()
            }
            2 -> {
                JobCancelledFragment()
            }
            else -> {
                Fragment()
            }
        }
    }
}