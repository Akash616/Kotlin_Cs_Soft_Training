package com.jetpackcomponents.livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel

    private val tv_facts : TextView
        get() = findViewById(R.id.tv_facts)

    private val btn_update : Button
        get() = findViewById(R.id.btn_update)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        mainViewModel.factsLiveData.observe(this, Observer { //simple lambda function
            tv_facts.text = it
        })

        btn_update.setOnClickListener {
            mainViewModel.updateLiveData()
        }

    }
}