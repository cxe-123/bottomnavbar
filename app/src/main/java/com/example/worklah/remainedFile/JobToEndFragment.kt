package com.example.worklah.remainedFile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.worklah.adapter.JobToEndAdapter
import com.example.worklah.data.JobClaimed
import com.example.worklah.databinding.FragmentJobToEndBinding
import com.google.firebase.firestore.*

class JobToEndFragment : Fragment() {
    private var _binding: FragmentJobToEndBinding? = null
    private val binding get() = _binding!!

    private lateinit var jobToEndList: MutableList<JobClaimed>
    private lateinit var jobAdapter: JobToEndAdapter
    private lateinit var db: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentJobToEndBinding.inflate(inflater, container, false)

//        jobAdapter = JobTrackingListItemAdapter1(requireContext(), jobToEndList)
//        binding.recyclerView.adapter = jobAdapter
//        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.setHasFixedSize(true)

        jobToEndList = mutableListOf()

        jobAdapter = JobToEndAdapter(requireContext(), jobToEndList)
        binding.recyclerView.adapter = jobAdapter

        readFirestoreData()
    }

    private fun readFirestoreData() {
        db = FirebaseFirestore.getInstance()
        db.collection("JobToEndList")
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
                            jobToEndList.add(document.document.toObject(JobClaimed::class.java))
                        }
                    }

                    jobAdapter.notifyDataSetChanged()
                }
            })
    }
}