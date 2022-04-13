package com.example.worklah.remainedFile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.worklah.adapter.JobToStartAdapter
import com.example.worklah.data.JobClaimed
import com.example.worklah.databinding.FragmentJobToStartBinding
import com.google.firebase.firestore.*

class JobToStartFragment : Fragment() {
    private var _binding: FragmentJobToStartBinding? = null
    private val binding get() = _binding!!

    private lateinit var jobToStartList: MutableList<JobClaimed>
    private lateinit var jobAdapter: JobToStartAdapter
    private lateinit var db: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentJobToStartBinding.inflate(inflater, container, false)

//        jobAdapter = JobTrackingListItemAdapter1(requireContext(), jobToStartList)
//        binding.recyclerView.adapter = jobAdapter
//        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.setHasFixedSize(true)

        jobToStartList = mutableListOf()

        jobAdapter = JobToStartAdapter(requireContext(), jobToStartList)
        binding.recyclerView.adapter = jobAdapter

        readFirestoreData()
    }

    private fun readFirestoreData() {
        db = FirebaseFirestore.getInstance()
        db.collection("JobToStartList")
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
                            jobToStartList.add(document.document.toObject(JobClaimed::class.java))
                        }
                    }

                    jobAdapter.notifyDataSetChanged()
                }
            })
    }
}