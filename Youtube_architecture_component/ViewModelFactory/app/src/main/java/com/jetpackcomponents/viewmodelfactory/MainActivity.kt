package com.jetpackcomponents.viewmodelfactory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    lateinit var txtCounter : TextView
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*ViewModel ka object hum kudh say ni banata hai, toh parameter kasa pass karanga
            ViewModelFactory - viewmodel ka object bana ka dati hai*/
        //mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        //Parameterized ViewModel Initialized
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(10)).get(MainViewModel::class.java)

        txtCounter =  findViewById(R.id.txtCounter)
        setText()

    }

    fun setText() {
        txtCounter.text = mainViewModel.count.toString()
    }

    fun increment(view: View) {
        mainViewModel.increment()
        setText()
    }

}