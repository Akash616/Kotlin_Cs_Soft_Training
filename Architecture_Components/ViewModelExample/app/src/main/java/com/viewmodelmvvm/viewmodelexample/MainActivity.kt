package com.viewmodelmvvm.viewmodelexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.viewmodelmvvm.viewmodelexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //MVVM? Mvvm architecture gives your project a proper organized
    //structure with the help of its 3 components:
    //Model - View - ViewModel
    /* View (UI, View is responsible only for UI)
    *  Model (DATA, Model is responsible for only data)
    *  ViewModel (acts as a bridge b/w view and model it's responsible for
    *           preparing and managing b/w data and UI.)*/

    private lateinit var binding: ActivityMainBinding
    private lateinit var myViewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        //ViewModelProvider: is a utility class provided by ViewModel library, that help you to
        //create and retrieve instances of viewmodel classes.
        binding.tvCounter.text = myViewModel.getInitialCounter().toString()
        binding.btnIncrement.setOnClickListener {
            binding.tvCounter.text = myViewModel.getUpdatedCounter().toString()
        }

    }
}