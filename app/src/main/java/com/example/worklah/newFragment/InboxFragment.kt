package com.example.worklah.newFragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.worklah.R
import com.example.worklah.adapter.InboxAdapter
import com.example.worklah.data.Inbox
import com.example.worklah.databinding.FragmentInboxBinding


class InboxFragment : Fragment() {
    private var _binding: FragmentInboxBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInboxBinding.inflate(inflater, container, false)

        var newInboxList = mutableListOf<Inbox>()
        var earlierInboxList = mutableListOf<Inbox>()

        newInboxList.add(Inbox(R.drawable.ic_envelope, "Help Us Improve", "Do you love our WorkLah app? Please rate us on Google Play Store! We would like to hear from you on areas of improvement.", "01 Apr"))
        newInboxList.add(Inbox(R.drawable.ic_attention, "Insurance Coverage", "New feature is launched! WorkLah is providing two insurance coverages to protect your rights! Check it out in your profile page.", "08 Mar"))
        newInboxList.add(Inbox(R.drawable.ic_clock, "Weekly Achievement", "Wow! You have completed 3 jobs this week. Keep it up!", "05 Mar"))
        earlierInboxList.add(Inbox(R.drawable.ic_attention, "Chat With Job Poster", "New feature is launched! You can now send message to the job poster in the job details page.", "09 Feb"))
        earlierInboxList.add(Inbox(R.drawable.ic_envelope, "Welcome To WorkLah", "Thanks for choosing us to lead a flexible working life. WorkLah is here to provide you all-in-one service for employment!", "02 Feb"))

        val recyclerView1 = binding.recyclerViewNew
        recyclerView1.layoutManager = LinearLayoutManager(context)
        recyclerView1.adapter = InboxAdapter(requireContext(), newInboxList)
        recyclerView1.setHasFixedSize(true)

        val recyclerView2 = binding.recyclerViewEarlier
        recyclerView2.layoutManager = LinearLayoutManager(context)
        recyclerView2.adapter = InboxAdapter(requireContext(), earlierInboxList)
        recyclerView2.setHasFixedSize(true)

        binding.markAllAsRead.setOnClickListener {
            newInboxList.clear()
            Toast.makeText(context, "This function is haven't done.", Toast.LENGTH_SHORT).show()
            //this.findNavController().navigate(R.id.action_inboxFragment_self)
        }

        return binding.root
    }

}