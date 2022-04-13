package com.example.worklah.data

import com.google.firebase.firestore.DocumentId

data class JobClaimed(
    @DocumentId val jobId: String? = null,
    val jobName: String? = null,
    val jobSalary: String? = null,
    val jobLocation: String? = null,
    val jobStartDate: String? = null,
    val jobEndDate: String? = null
)
