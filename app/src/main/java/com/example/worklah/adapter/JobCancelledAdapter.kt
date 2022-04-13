package com.example.worklah.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.worklah.R
import com.example.worklah.data.JobCancelled

class JobCancelledAdapter(
    private val context: Context,
    private val dataset: MutableList<JobCancelled>
) : RecyclerView.Adapter<JobCancelledAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.job_cancelled_card, viewGroup, false)

        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ItemViewHolder, position: Int) {
        viewHolder.jobName.text = dataset[position].jobName
        viewHolder.jobSalary.text = dataset[position].jobSalary
        viewHolder.jobLocation.text = dataset[position].jobLocation
        viewHolder.jobCancelDate.text = dataset[position].jobCancelDate
        //viewHolder.itemView.setOnClickListener{}
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val jobName: TextView = view.findViewById(R.id.list_item_job_name)
        val jobSalary: TextView = view.findViewById(R.id.list_item_job_salary)
        val jobLocation: TextView = view.findViewById(R.id.list_item_job_location)
        val jobCancelDate: TextView = view.findViewById(R.id.list_item_job_cancel_date)
        //val itemView = view
    }
}