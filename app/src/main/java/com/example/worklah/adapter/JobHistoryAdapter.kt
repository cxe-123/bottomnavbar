package com.example.worklah.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.worklah.R
import com.example.worklah.data.JobClaimed
import com.example.worklah.model.JobHistoryModel

class JobHistoryAdapter(private val context:Context,private val jobHist:List<JobHistoryModel>)
    :RecyclerView.Adapter<JobHistoryAdapter.jhViewHolder>(){

    class jhViewHolder(private val view: View):RecyclerView.ViewHolder(view){

        var jobHistTitle: TextView =view.findViewById(R.id.jobHistoryTitle)
        var jobHistCompany:TextView=view.findViewById(R.id.jobHistoryCompany)
        var jobHistCompanyAddress: TextView =view.findViewById(R.id.jobHistoryCompanyAddress)
        var jobHistSalary:TextView=view.findViewById(R.id.jobHistorySalary)
        var jobHistWorkingPeriod: TextView =view.findViewById(R.id.jobHistoryWorkingPeriod)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): jhViewHolder {
        val adapterLayout=LayoutInflater.from(parent.context).inflate(
            R.layout.job_history_card,
            parent,false)
        return jhViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: jhViewHolder, position: Int) {
        val item=jobHist[position]

        holder.jobHistTitle.text=context.resources.getString(item.jobHistoryTitle)
        holder.jobHistCompany.text=context.resources.getString(item.jobHistoryCompany)
        holder.jobHistCompanyAddress.text=context.resources.getString(item.jobHistoryCompanyAddress)
        holder.jobHistSalary.text=context.resources.getString(item.jobHistorySalary)
        holder.jobHistWorkingPeriod.text=context.resources.getString(item.jobHistoryWorkingPeriod)

    }

    override fun getItemCount(): Int {
        return jobHist.size
    }


}