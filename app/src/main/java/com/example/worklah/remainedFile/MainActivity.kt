package com.example.worklah.remainedFile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.worklah.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    //    Sign Up from Home Page by jiayi
    fun homeSignUp(view: View) {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }

    //    Log In from Home Page by jiayi
    fun homeLogIn(view: View) {
        val intent = Intent(this, LogInActivity::class.java)
        startActivity(intent)
    }

}