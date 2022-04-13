package com.example.worklah.remainedFile

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import com.example.worklah.databinding.ActivityLogInBinding
import com.google.firebase.auth.FirebaseAuth

class LogInActivity : AppCompatActivity() {

    //viewbinding
    private lateinit var binding: ActivityLogInBinding
    //progress dialog
    private lateinit var progressDialog: ProgressDialog
    //firebase auth
    //login only need email and password
    private lateinit var firebaseAuth: FirebaseAuth
    private var email=""
    private var password=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //configure progress dialog
        progressDialog=ProgressDialog(this)
        progressDialog.setTitle("Please wait")
        progressDialog.setMessage("Logging in...")
        progressDialog.setCanceledOnTouchOutside(false)

        //init firebaseAuth
        firebaseAuth=FirebaseAuth.getInstance()
        checkUser()

        //handle click,begin login
        binding.loginButton.setOnClickListener{
            //before logging in, validate data
            validateData()
        }

    }

    private fun validateData() {
        //get data
        email=binding.loginEmail.text.toString().trim()
        password=binding.loginPassword.text.toString().trim()

        //validate data
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            //invalid email format
            binding.loginEmail.error="Invalid email format."
        }else if(TextUtils.isEmpty(password)){
            //no password entered
            binding.loginPassword.error="Please enter password."
        }else{
            //data is validated, begin login
            firebaseLogin()
        }
    }

    private fun firebaseLogin() {
        //show progress
        progressDialog.show()
        firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                //login success
                progressDialog.dismiss()

                Toast.makeText(this,"Welcome back!", Toast.LENGTH_SHORT)
                    .show()

                //startActivity(Intent(this,AccountOverviewActivity::class.java))
                //startActivity(Intent(this, HomeActivity::class.java))
                startActivity(Intent(this, NavigationActivity::class.java))
                finish()
            }
            .addOnFailureListener{e->
                //login failed
                progressDialog.dismiss()
                Toast.makeText(this,"Login failed due to ${e.message}.", Toast.LENGTH_SHORT).show()
            }
    }

    private fun checkUser() {
        //if user is already logged in go to profile activity
        //get current user
        val firebaseUser = firebaseAuth.currentUser
        if(firebaseUser!=null){
            //user is already logged in
            //startActivity(Intent(this,AccountOverviewActivity::class.java))
            //startActivity(Intent(this, HomeActivity::class.java))
            startActivity(Intent(this, NavigationActivity::class.java))
            finish()
        }
    }

}