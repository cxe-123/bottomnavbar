package com.example.worklah.newFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.worklah.R
import com.example.worklah.adapter.JobHistoryAdapter
import com.example.worklah.adapter.JobToStartAdapter
import com.example.worklah.data.JobClaimed
import com.example.worklah.databinding.FragmentJobHistoryBinding
import com.google.firebase.firestore.*


class JobHistoryFragment : Fragment() {
    private var _binding: FragmentJobHistoryBinding? = null
    private val binding get() = _binding!!

    private lateinit var jobHistory: MutableList<JobClaimed>
    private lateinit var db: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentJobHistoryBinding.inflate(inflater, container, false)

        //set up title in action bar
        (activity as AppCompatActivity?)!!.supportActionBar?.title = "Job History"
        //set up up button
        (activity as AppCompatActivity?)!!.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //TO JY: i add the below 2 line for u to link back to prev page, the acc overview page
        binding.backButton.setOnClickListener{
            this.findNavController().navigate(R.id.action_jobHistoryFragment_to_profileFragment2)
        }

        jobHistory = mutableListOf()

        val jobHistoryRecyclerView=binding.jobHistoryRecycler
        //jobHistoryRecyclerView.adapter= JobHistoryAdapter(requireContext(),jobHistory)
        jobHistoryRecyclerView.setHasFixedSize(true)

        readFirestoreData()

        return binding.root
    }

    private fun readFirestoreData() {
        db = FirebaseFirestore.getInstance()
        db.collection("JobHistory")
            .addSnapshotListener(object : EventListener<QuerySnapshot> {
                override fun onEvent(
                    value: QuerySnapshot?,
                    error: FirebaseFirestoreException?
                ) {
                    if (error != null) {
                        Log.e("Firestore Error", error.toString())
                        return
                    }

                    for (document: DocumentChange in value?.documentChanges!!) {
                        if (document.type == DocumentChange.Type.ADDED) {
                            jobHistory.add(document.document.toObject(JobClaimed::class.java))
                        }
                    }

                    //JobHistoryAdapter(requireContext(),jobHistory).notifyDataSetChanged()
                }
            })
    }
}