package com.example.worklah.model

import androidx.annotation.StringRes

data class JobHistoryModel(
    @StringRes
    val jobHistoryTitle: Int,

    @StringRes
    val jobHistoryCompany: Int,

    @StringRes
    val jobHistoryCompanyAddress: Int,

    @StringRes
    val jobHistorySalary: Int,

    @StringRes
    val jobHistoryWorkingPeriod: Int
)



