package com.example.worklah.remainedFile

import com.example.worklah.R
import com.example.worklah.model.JobHistoryModel

class SetJobHistoryData {

    fun setJobHistories():List<JobHistoryModel>{
        return listOf<JobHistoryModel>(
            JobHistoryModel(
                R.string.job_position1,
                R.string.job_company1,
                R.string.job_company_address1,
                R.string.job_salary1,
                R.string.job_working_period1
            ),
            JobHistoryModel(
                R.string.job_position2,
                R.string.job_company2,
                R.string.job_company_address2,
                R.string.job_salary2,
                R.string.job_working_period2
            ),
            JobHistoryModel(
                R.string.job_position3,
                R.string.job_company3,
                R.string.job_company_address3,
                R.string.job_salary3,
                R.string.job_working_period3
            ),
            JobHistoryModel(
                R.string.job_position4,
                R.string.job_company4,
                R.string.job_company_address4,
                R.string.job_salary4,
                R.string.job_working_period4
            ), JobHistoryModel(
                R.string.job_position5,
                R.string.job_company5,
                R.string.job_company_address5,
                R.string.job_salary5,
                R.string.job_working_period5
            )

        )
    }

}