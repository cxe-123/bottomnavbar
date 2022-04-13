package com.example.worklah.data


import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class JobData(
     val jobImg: Int,
     val jobTitle:String? = null,
     val jobPrice:String? = null,
     val jobDescription:String? = null,
     val jobLocation:String? = null,
     val jobStartDate:String? = null,
     val jobEndDate:String? = null,
     val jobStartTime:String? = null,
     val jobEndTime:String? = null,
     val jobOverview:String? = null
    )
