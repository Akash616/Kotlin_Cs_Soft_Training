package com.example.importantexample.sharedpreferences

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import com.example.importantexample.R

class SharedPrefActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_pref)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        Handler(Looper.getMainLooper()).postDelayed(object : Runnable{
            override fun run() {
                var pref: SharedPreferences = getSharedPreferences("login", MODE_PRIVATE)
                val check: Boolean = pref.getBoolean("flag", false) //if pref. is not there defValue
                val iNext: Intent
                if (check){ //for True (User is logged in)
                    iNext = Intent(this@SharedPrefActivity, HomeActivity::class.java)
                }else{ // for False (either first time or User is logged out)
                    iNext = Intent(this@SharedPrefActivity, LoginActivity::class.java)
                }
                startActivity(iNext)
            }
        }, 3000)
        
    }
}