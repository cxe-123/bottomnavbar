package com.example.worklah.remainedFile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.worklah.adapter.JobCancelledAdapter
import com.example.worklah.data.JobCancelled
import com.example.worklah.databinding.FragmentJobCancelledBinding
import com.google.firebase.firestore.*

class JobCancelledFragment : Fragment() {
    private var _binding: FragmentJobCancelledBinding? = null
    private val binding get() = _binding!!

    private lateinit var jobCancelledList: MutableList<JobCancelled>
    private lateinit var jobAdapter: JobCancelledAdapter
    private lateinit var db: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentJobCancelledBinding.inflate(inflater, container, false)

//        jobAdapter = JobTrackingListItemAdapter1(requireContext(), jobCancelledList)
//        binding.recyclerView.adapter = jobAdapter
//        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.setHasFixedSize(true)

        jobCancelledList = mutableListOf()

        jobAdapter = JobCancelledAdapter(requireContext(), jobCancelledList)
        binding.recyclerView.adapter = jobAdapter

        readFirestoreData()
    }

    private fun readFirestoreData() {
        db = FirebaseFirestore.getInstance()
        db.collection("JobCancelledList")
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
                            jobCancelledList.add(document.document.toObject(JobCancelled::class.java))
                        }
                    }

                    jobAdapter.notifyDataSetChanged()
                }
            })
    }
}