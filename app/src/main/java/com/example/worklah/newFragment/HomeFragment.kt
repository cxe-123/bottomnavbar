package com.example.worklah.newFragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.worklah.remainedFile.DetailActivity
import com.example.worklah.R
import com.example.worklah.adapter.HomeAdapter
import com.example.worklah.data.JobData
import com.example.worklah.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var home: ImageView
    private lateinit var work: ImageView
    private lateinit var inbox: ImageView
    private lateinit var profile: ImageView

    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<JobData>
    lateinit var jobT : Array<String>
    lateinit var jobP : Array<String>
    lateinit var jobD : Array<String>
    lateinit var jobL : Array<String>
    lateinit var jobSD : Array<String>
    lateinit var jobED : Array<String>
    lateinit var jobST : Array<String>
    lateinit var jobET : Array<String>
    lateinit var jobO : Array<String>
    lateinit var jobImg : Array<Int>
    lateinit var description : Array<String>
    lateinit var requirement : Array<String>
    lateinit var requirementInfo : Array<String>
    lateinit var locationAndTime : Array<String>
    lateinit var posterName : Array<String>

    private lateinit var tempArrayList : ArrayList<JobData>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        /*
        //BELOW IS THE CODE FOR ORIGINAL BOTTOM NAV BAR WHICH USING INTENT

        home = binding.iconHome
        work = binding.iconWork
        inbox = binding.iconInbox
        profile = binding.iconProfile

        home.setOnClickListener{
            startActivity(Intent(this,this::class.java))
            //startActivity(getIntent())
        }
        work.setOnClickListener {
            startActivity(Intent(this, WorkActivity::class.java))
        }
        inbox.setOnClickListener {
            startActivity(Intent(this, InboxActivity::class.java))
        }
        profile.setOnClickListener {
            startActivity(Intent(this, AccountOverviewActivity::class.java))
        }
        */

        jobImg = arrayOf(
            R.drawable.job1,
            R.drawable.job2,
            R.drawable.job3,
            R.drawable.job4
        )

        jobT = arrayOf(
            "Freelance Contract IT",
            "Freelance Video Editor",
            "Pickers and Packers",
            "Event helper"
        )

        jobP = arrayOf(
            "2500",
            "195",
            "96",
            "120"
        )

        jobD = arrayOf(
            "Work with IT related project",
            "Edit videos and add effects/elements",
            "Helping to do warehousing work",
            "Execution of various programmes and events"
        )

        jobL = arrayOf(
            "A 17 Jln 21/11B Seksyen 21 Petaling Jaya",
            "8th Floor Kompleks Jln Sultan",
            "Jalan Pasir Merah, New Pasir Puteh, 31650",
            "1st Floor, Bangunan IBEX, Jalan 222"
        )

        jobSD = arrayOf(
            "12 Feb",
            "15 Feb",
            "20 Feb",
            "1 April"
        )

        jobED = arrayOf(
            "12 Mac",
            "17 Mac",
            "20 Feb",
            "1 April"
        )

        jobST = arrayOf(
            "10.00am",
            "1.00pm",
            "10.00am",
            "8.00am"
        )

        jobET = arrayOf(
            "3.00pm",
            "5.00pm",
            "6.00pm",
            "8.00pm"
        )

        jobO = arrayOf(
            "1 month shift | RM16/hour",
            "3 Shift | RM13/hour",
            "1 Shift | RM12/hour",
            "1 Shift | RM10/hour"
        )

        description = arrayOf(
            getString(R.string.job_description_text_1),
            getString(R.string.job_description_text_2),
            getString(R.string.job_description_text_3),
            getString(R.string.job_description_text_4)
        )

        requirement = arrayOf(
            getString(R.string.requirement_text_1),
            getString(R.string.requirement_text_2),
            getString(R.string.requirement_text_3),
            getString(R.string.requirement_text_4)
        )

        requirementInfo = arrayOf(
            getString(R.string.requirement1),
            getString(R.string.requirement2),
            getString(R.string.requirement3),
            getString(R.string.requirement4)
        )

        locationAndTime = arrayOf(
            getString(R.string.location_and_time_text_1),
            getString(R.string.location_and_time_text_2),
            getString(R.string.location_and_time_text_3),
            getString(R.string.location_and_time_text_4),
        )

        posterName = arrayOf(
            getString(R.string.poster_name_1),
            getString(R.string.poster_name_2),
            getString(R.string.poster_name_3),
            getString(R.string.poster_name_4),
        )

        newRecyclerView = binding.myRecycler
        newRecyclerView.layoutManager = LinearLayoutManager(context)
        newRecyclerView.setHasFixedSize(true)

        newArrayList = arrayListOf<JobData>()
        tempArrayList = arrayListOf<JobData>()
        getUserdata()

        return binding.root
    }

    private fun getUserdata() {
        for(i in jobImg.indices){
            val jobs = JobData(jobImg[i],jobT[i],jobP[i],jobD[i],jobL[i],jobSD[i],jobED[i],jobST[i],jobET[i],jobO[i])
            newArrayList.add(jobs)
        }


        var adapter = HomeAdapter(newArrayList)
        newRecyclerView.adapter = adapter
        adapter.setOnItemClickListener(object : HomeAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                //Toast.makeText(this@MainActivity,"You Clicked on item no. $position",Toast.LENGTH_SHORT).show()

                //ori: val intent = Intent(this@HomeActivity, DetailActivity::class.java)
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra("jobT",newArrayList[position].jobTitle)
                intent.putExtra("jobP",newArrayList[position].jobPrice)
                intent.putExtra("jobD",newArrayList[position].jobDescription)
                intent.putExtra("jobL",newArrayList[position].jobLocation)
                intent.putExtra("jobSD",newArrayList[position].jobStartDate)
                intent.putExtra("jobED",newArrayList[position].jobEndDate)
                intent.putExtra("jobST",newArrayList[position].jobStartTime)
                intent.putExtra("jobET",newArrayList[position].jobEndTime)
                intent.putExtra("description",description[position])
                intent.putExtra("requirement",requirement[position])
                intent.putExtra("requirementInfo",requirementInfo[position])
                intent.putExtra("locationAndTime",locationAndTime[position])
                intent.putExtra("posterName",posterName[position])
                startActivity(intent)
            }

        })
    }
}

