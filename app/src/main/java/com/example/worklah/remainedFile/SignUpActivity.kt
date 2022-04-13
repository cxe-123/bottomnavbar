package com.example.worklah.remainedFile

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.worklah.databinding.ActivitySignUpBinding
import com.example.worklah.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class SignUpActivity : AppCompatActivity() {

    //view binding
    private lateinit var binding: ActivitySignUpBinding
    //progress dialog
    private lateinit var progressDialog: ProgressDialog
    //firebase auth
    private lateinit var firebaseAuth: FirebaseAuth
    //realtime database
    private lateinit var databaseReference:DatabaseReference
    //user data fields
    private var uid=""
    private var fName = ""
    private var lName = ""
    private var gender = ""
    private var ic = ""
    private var dob = ""
    private var email = ""
    private var phone = ""
    private var address = ""
    private var bankAcc = ""
    private var password = ""
    private var passwordCfm = ""

    override fun onCreate(savedInstanceState:Bundle?){
        super.onCreate(savedInstanceState)
        binding= ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //configure progress dialog
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please wait")
        progressDialog.setMessage("Creating account...")
        progressDialog.setCanceledOnTouchOutside(false)

        //init firebase auth
        firebaseAuth = FirebaseAuth.getInstance()

        //get uid
        uid= firebaseAuth.currentUser?.uid.toString()

        //init real time database
        databaseReference = Firebase.database.getReference("Users")

        //handle click, begin signup
        binding.signUpButton.setOnClickListener{
            validateData()
        }
    }

    private fun validateData() {

        //get data
        fName = binding.signupFirstName.text.toString().trim()
        lName = binding.signupLastName.text.toString().trim()
        ic = binding.signupIC.text.toString().trim()
        dob = binding.signupDOB.text.toString().trim()
        email = binding.signupEmail.text.toString().trim()
        phone = binding.signupContactNo.text.toString().trim()
        address = binding.signupAddress.text.toString().trim()
        bankAcc = binding.signupBankAcc.text.toString().trim()
        password = binding.signupPassword.text.toString().trim()
        passwordCfm = binding.signupPasswordConfirmation.text.toString().trim()

        val genderRadioButtonID=binding.genderGroup.checkedRadioButtonId
        val genderRadioButton=findViewById<RadioButton>(genderRadioButtonID).text
        if(genderRadioButton=="Male"){
            gender="Male"
        }else{
            gender="Female"
        }

        //validate data
        //check null first
        if (TextUtils.isEmpty(fName)) {
            binding.signupFirstName.error = "Please enter first name."
        } else if (TextUtils.isEmpty(lName)) {
            binding.signupLastName.error = "Please enter last name."
        } else if (TextUtils.isEmpty(ic)) {
            binding.signupIC.error = "Please enter IC."
        } else if (TextUtils.isEmpty(dob)) {
            binding.signupDOB.error = "Please enter birthday."
        } else if (TextUtils.isEmpty(email)) {
            binding.signupEmail.error = "Please enter email."
        } else if (TextUtils.isEmpty(phone)) {
            binding.signupContactNo.error = "Please enter contact no."
        } else if (TextUtils.isEmpty(address)) {
            binding.signupAddress.error = "Please enter address."
        } else if (TextUtils.isEmpty(bankAcc)) {
            binding.signupBankAcc.error = "Please enter JuneBank account no."
        } else if (TextUtils.isEmpty(password)) {
            binding.signupPassword.error = "Please enter password."
        } else if (TextUtils.isEmpty(passwordCfm)) {
            binding.signupPasswordConfirmation.error = "Please enter password again."
        } else {

            //check pattern
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                //invalid email format
                binding.signupEmail.error = "Invalid email format."
            } else if (!Patterns.PHONE.matcher(phone).matches()) {
                //invalid phone format
                binding.signupContactNo.error = "Invalid contact format."
            } else if (password != passwordCfm) {
                binding.signupPasswordConfirmation.error = "Different password detected."
            } else {
                //data valid, continue signup
                firebaseSignUp()
            }
        }
    }

    private fun firebaseSignUp() {
        //show progress
        progressDialog.show()

        val user= User(fName,lName,gender,ic,dob,email,phone,address,bankAcc,password,passwordCfm)

        //write user email and password in auth database
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener {
                //write user info in realtime database
                databaseReference.child(firebaseAuth.currentUser!!.uid).setValue(user)
                //signup success
                progressDialog.dismiss()

                Toast.makeText(this,"Account created successfully.", Toast.LENGTH_SHORT).show()

                //startActivity(Intent(this,AccountOverviewActivity::class.java))
                //startActivity(Intent(this, HomeActivity::class.java))
                startActivity(Intent(this, NavigationActivity::class.java))
                finish()
        }
            .addOnFailureListener {e->
                //signup failed
                progressDialog.dismiss()
                Toast.makeText(this,"Signup Failed due to ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onSupportNavigateUp():Boolean{
        onBackPressed() //go back to previous activity, when back button of actionbar clicked
        return super.onSupportNavigateUp()
    }
}