package com.viewbinding.viewbindingexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.viewbinding.viewbindingexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //private lateinit var tvDemo: TextView //Traditional method
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //binding.tvDemo.setText("name")
        //binding.tvDemo.text = "akash"
        binding.tvDemo.setOnClickListener {
            binding.tvDemo.text = "Akash Gupta"
        }

    }
}

/* Use view binding in fragments
* https://developer.android.com/topic/libraries/view-binding#kotlin
* https://www.geeksforgeeks.org/view-binding-with-fragments-in-android-jetpack/
* */