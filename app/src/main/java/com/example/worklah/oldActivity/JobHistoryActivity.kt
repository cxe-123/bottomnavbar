package com.example.worklah.oldActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.worklah.R
import com.example.worklah.remainedFile.SetJobHistoryData
import com.example.worklah.adapter.JobHistoryAdapter

class JobHistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_history)

        //set up title in action bar
        supportActionBar?.title = "Job History"
        //set up up button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val jobHistoryDataset= SetJobHistoryData().setJobHistories()

        val jobHistoryRecyclerView=findViewById<RecyclerView>(R.id.jobHistoryRecycler)
        jobHistoryRecyclerView.adapter= JobHistoryAdapter(this,jobHistoryDataset)
        jobHistoryRecyclerView.setHasFixedSize(true)

    }
}