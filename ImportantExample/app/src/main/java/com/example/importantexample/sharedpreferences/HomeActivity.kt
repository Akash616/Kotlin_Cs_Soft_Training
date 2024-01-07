package com.example.importantexample.sharedpreferences

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.importantexample.R

class HomeActivity : AppCompatActivity() {

    lateinit var btnLogout: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        btnLogout = findViewById(R.id.btnLogout)

        btnLogout.setOnClickListener {
            val pref: SharedPreferences = getSharedPreferences("login", MODE_PRIVATE)
            val editor: SharedPreferences.Editor = pref.edit()
            editor.putBoolean("flag", false)
            editor.apply()
            val iLogin = Intent(this@HomeActivity, LoginActivity::class.java)
            startActivity(iLogin)
        }


    }
}