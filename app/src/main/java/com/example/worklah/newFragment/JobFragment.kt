package com.example.worklah.newFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.worklah.adapter.ViewPagerAdapter
import com.example.worklah.databinding.FragmentJobBinding
import com.google.android.material.tabs.TabLayoutMediator

class JobFragment : Fragment() {
    private var _binding: FragmentJobBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentJobBinding.inflate(inflater, container, false)

        //TAB LAYOUT & VIEW PAGER
        val tabLayout = binding.tabLayout
        val viewPager2 = binding.viewPager2

        //val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        val adapter = ViewPagerAdapter(parentFragmentManager, lifecycle)

        viewPager2.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager2){tab, position ->
            when(position){
                0 -> {
                    tab.text = "To Start"
                }
                1 -> {
                    tab.text = "To End"
                }
                2 -> {
                    tab.text = "Cancelled"
                }
            }
        }.attach()

        return binding.root
    }

}