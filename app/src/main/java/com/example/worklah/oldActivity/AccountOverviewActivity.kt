package com.example.worklah.oldActivity

import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.worklah.remainedFile.MainActivity
import com.example.worklah.databinding.ActivityAccountOverviewBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class AccountOverviewActivity : AppCompatActivity() {

    //view binding
    private lateinit var binding:ActivityAccountOverviewBinding
    //firebaseAuth
    private lateinit var firebaseAuth: FirebaseAuth
    //realtime database
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAccountOverviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //init firebase auth
        firebaseAuth=FirebaseAuth.getInstance()
        checkUser()

        //handle click, logout
        binding.logoutButton.setOnClickListener{
            firebaseAuth.signOut()
            checkUser()
        }
    }

    private fun checkUser() {

        //check user is logged in or not
        val firebaseUser=firebaseAuth.currentUser

        //user not null, user is logged in
        if(firebaseUser!=null){

            //get data from database
            databaseReference = Firebase.database.getReference("Users").child(firebaseUser.uid)

            databaseReference.addValueEventListener(object : ValueEventListener {

                override fun onDataChange(dataSnapshot: DataSnapshot) {

                    //update user profile
                    binding.userFirstName.text=dataSnapshot.child("fname")
                        .getValue<String>()
                    binding.userLastName.text=dataSnapshot.child("lname").getValue<String>()
                    binding.userEmail.text=dataSnapshot.child("email").getValue<String>()
                    binding.userContactNo.text=dataSnapshot.child("phone").getValue<String>()
                    binding.userIC.text=dataSnapshot.child("ic").getValue<String>()
                    binding.userDOB.text=dataSnapshot.child("dob").getValue<String>()
                    binding.userAddress.text=dataSnapshot.child("address").getValue<String>()
                    binding.userBank.text=dataSnapshot.child("bankAcc").getValue<String>()

                    val gender=dataSnapshot.child("gender").getValue<String>()
                    if(binding.userMale.text==gender){
                        binding.userMale.isChecked=true
                        binding.userMale.setTextColor(Color.parseColor("#FFFFFF"))
                    }else{
                        binding.userFemale.isChecked=true
                        binding.userFemale.setTextColor(Color.parseColor("#FFFFFF"))
                    }

                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.w(TAG, "Failed to read value.", error.toException())
                }
            })

        }else{
            //user is null, user is not logged in, goto login activity
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }

    fun viewJobHistory(view: android.view.View) {
        val intent = Intent(this, JobHistoryActivity::class.java)
        startActivity(intent)
    }


}

