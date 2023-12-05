package com.kotlinpratice.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.kotlinpratice.myapplication.api.QuoteService
import com.kotlinpratice.myapplication.api.RetrofitHelper
import com.kotlinpratice.myapplication.databinding.ActivityMainBinding
import com.kotlinpratice.myapplication.viewmodel.MainViewModel
import com.kotlinpratice.myapplication.viewmodel.factory.MainViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel
    lateinit var quoteService: QuoteService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userAPi = RetrofitHelper.getRetrofitInstance().create(QuoteService::class.java)

        mainViewModel = ViewModelProvider(this, MainViewModelFactory(userAPi)).get(MainViewModel::class.java)

        mainViewModel.quoteLiveData.observe(this, Observer {
            it.results.forEach {
                Log.d("Quotes", it.content)
            }
            for (i in 0 until it.results.size){
                binding.tvQuotes.text = it.results[i].author //not working
            }
        })

        binding.btnGetQuotes.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                mainViewModel.getQuotes(1)
            }
        }

    }

}