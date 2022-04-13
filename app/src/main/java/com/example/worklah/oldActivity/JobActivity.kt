package com.example.worklah.oldActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.worklah.R
import com.example.worklah.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class JobActivity : AppCompatActivity() {
    //late init var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = ActivityMainBinding.inflate(layoutInflater)
        //setContentView(binding.root)
        setContentView(R.layout.activity_main)

        //TAB LAYOUT & VIEW PAGER
        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        val viewPager2 = findViewById<ViewPager2>(R.id.view_pager_2)

        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)

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

        /*
        //MESSAGE ACTIVITY
        newMessageList.add(Inbox(R.drawable.test_image, "Title", "Message content.", "01 Jan"))
        newMessageList.add(Inbox(R.drawable.test_image, "Title", "Message content.", "01 Jan"))
        newMessageList.add(Inbox(R.drawable.test_image, "Title", "Message content.", "01 Jan"))

        earlierMessageList.add(Inbox(R.drawable.test_image, "Title", "Message content.", "01 Jan"))
        earlierMessageList.add(Inbox(R.drawable.test_image, "Title", "Message content.", "01 Jan"))
        earlierMessageList.add(Inbox(R.drawable.test_image, "Title", "Message content.", "01 Jan"))

         */
    }
}