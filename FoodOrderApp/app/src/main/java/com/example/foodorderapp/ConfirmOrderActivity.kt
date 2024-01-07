package com.example.foodorderapp

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.foodorderapp.databinding.ActivityConfirmOrderBinding

class ConfirmOrderActivity : AppCompatActivity() {

    lateinit var binding: ActivityConfirmOrderBinding
    lateinit var confirmOrderActivityAdapter: ConfirmOrderActivityAdapter
    lateinit var context: Activity
    lateinit var updateSelectedItem: UpdateSelectedItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.title = "Confirm Order"

        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }



    }
}