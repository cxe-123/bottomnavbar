package com.example.worklah.adapter

import android.app.AlertDialog
import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.worklah.R
import com.example.worklah.data.JobClaimed
import com.google.firebase.firestore.FirebaseFirestore
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class JobToStartAdapter(
    private val context: Context?,
    private val dataset: MutableList<JobClaimed>
) : RecyclerView.Adapter<JobToStartAdapter.ItemViewHolder>() {

    private var db = FirebaseFirestore.getInstance()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.job_to_start_card, viewGroup, false)

        return ItemViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(viewHolder: ItemViewHolder, position: Int) {
        viewHolder.jobName.text = dataset[position].jobName
        viewHolder.jobSalary.text = dataset[position].jobSalary
        viewHolder.jobLocation.text = dataset[position].jobLocation
        viewHolder.jobStartDate.text = dataset[position].jobStartDate
        viewHolder.jobEndDate.text = dataset[position].jobEndDate
        viewHolder.buttonCancel.setOnClickListener {
            AlertDialog.Builder(context)
                .setTitle("Cancel")
                //.setIcon(R.drawable.ic_warning)
                .setMessage("Are you sure to cancel the job, ${dataset[position].jobName}?")
                .setPositiveButton("Yes") { dialog, _ ->

                    //add into JobCancelledList

                    val data = hashMapOf(
                        "jobName" to dataset[position].jobName,
                        "jobSalary" to dataset[position].jobSalary,
                        "jobLocation" to dataset[position].jobLocation,
                        "jobCancelDate" to LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMM")),
                    )

                    db.collection("JobCancelledList")
                        .add(data)
                        .addOnSuccessListener { Log.d("Firestore Add", "Success") }
                        .addOnFailureListener { Log.w("Firestore Add", "Failed") }

                    //delete from JobToStartList

                    db.collection("JobToStartList").document(dataset[position].jobId!!)
                        .delete()
                        .addOnSuccessListener { Log.d("Firestore Delete", "Success") }
                        .addOnFailureListener { Log.w("Firestore Delete", "Failed") }

                    notifyDataSetChanged()

                    Toast.makeText(context, "Job is cancelled.", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
                .setNegativeButton("No") { dialog, _ ->
                    Toast.makeText(context, "Job is not cancelled.", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
                .create()
                .show()
        }
        viewHolder.buttonStart.setOnClickListener {
            AlertDialog.Builder(context)
                .setTitle("Start")
                //.setIcon(R.drawable.ic_warning)
                .setMessage("Are you sure to start the job, ${dataset[position].jobName}?")
                .setPositiveButton("Yes") { dialog, _ ->

                    //add into JobToEndList

                    val data = hashMapOf(
                        "jobName" to dataset[position].jobName,
                        "jobSalary" to dataset[position].jobSalary,
                        "jobLocation" to dataset[position].jobLocation,
                        "jobStartDate" to LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMM")),
                        "jobEndDate" to dataset[position].jobEndDate
                    )

                    db.collection("JobToEndList")
                        .add(data)
                        .addOnSuccessListener { Log.d("Firestore Add", "Success") }
                        .addOnFailureListener { Log.w("Firestore Add", "Failed") }

                    //delete from JobToStartList

                    db.collection("JobToStartList").document(dataset[position].jobId!!)
                        .delete()
                        .addOnSuccessListener { Log.d("Firestore Delete", "Success") }
                        .addOnFailureListener { Log.w("Firestore Delete", "Failed") }

                    notifyDataSetChanged()

                    Toast.makeText(context, "Job is started.", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
                .setNegativeButton("No") { dialog, _ ->
                    Toast.makeText(context, "Job is not started.", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
                .create()
                .show()
        }
        /*
        viewHolder.itemView.setOnClickListener{
            Toast.makeText(context, "Entered new page.", Toast.LENGTH_SHORT).show()
        }
         */
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val jobName: TextView = view.findViewById(R.id.list_item_job_name)
        val jobSalary: TextView = view.findViewById(R.id.list_item_job_salary)
        val jobLocation: TextView = view.findViewById(R.id.list_item_job_location)
        val jobStartDate: TextView = view.findViewById(R.id.list_item_job_start_date)
        val jobEndDate: TextView = view.findViewById(R.id.list_item_job_end_date)
        val buttonCancel: Button = view.findViewById(R.id.list_item_button_cancel)
        val buttonStart: Button = view.findViewById(R.id.list_item_button_start)
        //val itemView = view
    }
}