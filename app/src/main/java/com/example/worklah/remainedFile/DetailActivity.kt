package com.example.worklah.remainedFile

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.worklah.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class DetailActivity : AppCompatActivity() {

    private lateinit var claimButton: Button
    private lateinit var builder: AlertDialog.Builder
    private lateinit var chatButton: ImageButton
    private lateinit var backButton: ImageView

    private lateinit var callButton: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val description: TextView = findViewById(R.id.job_description_text)
        val jobT: TextView = findViewById(R.id.job_title)
        val jobD: TextView = findViewById(R.id.job_info)
        val requirement: TextView = findViewById(R.id.job_requirement)
        val requirementInfo: TextView = findViewById(R.id.requirement_info)
        val locationAndTime: TextView = findViewById(R.id.job_location_time)
        val jobL: TextView = findViewById(R.id.job_location_info)
        val jobSD: TextView = findViewById(R.id.job_date_info1_1)
        val jobED: TextView = findViewById(R.id.job_date_info1_2)
        val jobST: TextView = findViewById(R.id.job_date_info1_3)
        val jobET: TextView = findViewById(R.id.job_date_info1_4)
        val jobP: TextView = findViewById(R.id.price)
        val posterName: TextView = findViewById(R.id.poster_name)

        val bundle: Bundle? = intent.extras
        val jobTitle = bundle!!.getString("jobT")
        val jobPrice = bundle.getString("jobP")
        val jobDescription = bundle.getString("jobD")
        val jobLocation = bundle.getString("jobL")
        val jobStartDate = bundle.getString("jobSD")
        val jobEndDate = bundle.getString("jobED")
        val jobStartTime = bundle.getString("jobST")
        val jobEndTime = bundle.getString("jobET")
        val jobDescriptionText = bundle.getString("description")
        val jobRequirementText = bundle.getString("requirement")
        val jobRequirementInfo = bundle.getString("requirementInfo")
        val jobLocationAndTime = bundle.getString("locationAndTime")
        val jobPosterName = bundle.getString("posterName")

        description.text = jobDescriptionText
        jobT.text = jobTitle
        jobD.text = jobDescription
        requirement.text = jobRequirementText
        requirementInfo.text = jobRequirementInfo
        locationAndTime.text = jobLocationAndTime
        jobL.text = jobLocation
        jobSD.text = jobStartDate
        jobED.text = jobEndDate
        jobST.text = jobStartTime
        jobET.text = jobEndTime
        jobP.text = jobPrice
        posterName.text = jobPosterName

        claimButton = findViewById(R.id.claim_button)
        builder = AlertDialog.Builder(this)

        claimButton.setOnClickListener {
            if(claimButton.text == "CLAIM") {
                builder.setTitle("")
                    .setMessage("Are you sure you want to claim this job ?")
                    .setCancelable(true)

                    .setPositiveButton("Yes") { dialogInterface, it ->
                        //pass data to database
                        saveFireStore(
                            jobT.text.toString(),
                            jobP.text.toString(),
                            jobL.text.toString(),
                            jobSD.text.toString(),
                            jobED.text.toString()
                        )

                        val claimText = "CLAIMED"
                        claimButton.text = claimText


                        dialogInterface.cancel()
                    }
                    .setNegativeButton("No") { dialogInterface, it ->

                        dialogInterface.cancel()
                    }

                    .show()

            }
        }

        chatButton = findViewById(R.id.icon_chat1)
        chatButton.setOnClickListener{
            val intent = Intent(this@DetailActivity, ChatActivity::class.java)
            intent.putExtra("posterName", jobPosterName)
            intent.putExtra("uid", FirebaseAuth.getInstance().currentUser?.uid)
            startActivity(intent)
        }

        backButton = findViewById(R.id.back_button)
        backButton.setOnClickListener(){
            onBackPressed()
        }

        callButton = findViewById(R.id.icon_call1)
        callButton.setOnClickListener{
            val phoneNumber = "12345678"
            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.data = Uri.parse("tel:$phoneNumber")

            if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED ){
                ActivityCompat.requestPermissions(this,arrayOf(android.Manifest.permission.CALL_PHONE),1)
            }else{
                startActivity(callIntent)
            }

        }
    }

    private fun saveFireStore(jobT:String, jobP:String, jobL:String, jobSD:String, jobED:String){
        val db = FirebaseFirestore.getInstance()
        val job: MutableMap<String,Any> = HashMap()
        job["jobName"] = jobT
        job["jobSalary"] = jobP
        job["jobLocation"] = jobL
        job["jobStartDate"] = jobSD
        job["jobEndDate "] = jobED


        db.collection("JobToStartList")
            .add(job)
            .addOnSuccessListener {
            }

    }


}
