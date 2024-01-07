package com.example.importantexample.sharedpreferences

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.importantexample.R

class LoginActivity : AppCompatActivity() {

    lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin = findViewById(R.id.btnLogin)
        btnLogin.setOnClickListener {
            //code for verification

            val pref: SharedPreferences = getSharedPreferences("login", MODE_PRIVATE)
            val editor: SharedPreferences.Editor = pref.edit()
            editor.putBoolean("flag", true)
            editor.apply()
            val iHome = Intent(this@LoginActivity, HomeActivity::class.java)
            startActivity(iHome)
        }

    }
}