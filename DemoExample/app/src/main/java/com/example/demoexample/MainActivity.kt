package com.example.demoexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.demoexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val topHeadlinesFragment = TopHeadlinesFragment()
        supportFragmentManager.beginTransaction().replace(R.id.mainContainer, topHeadlinesFragment)

    }
}