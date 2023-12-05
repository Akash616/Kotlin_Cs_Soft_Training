package com.jetpackcomponents.mvvmwithretrofit

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jetpackcomponents.mvvmwithretrofit.api.QuoteService
import com.jetpackcomponents.mvvmwithretrofit.api.RetrofitHelper
import com.jetpackcomponents.mvvmwithretrofit.models.ResultX
import com.jetpackcomponents.mvvmwithretrofit.repository.QuoteRepository
import com.jetpackcomponents.mvvmwithretrofit.viewmodels.MainViewModel
import com.jetpackcomponents.mvvmwithretrofit.viewmodels.MainViewModelFactory
import java.io.File
import java.nio.channels.FileChannel

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    lateinit var tv_demo : TextView
    lateinit var iv_profile : ImageView
    lateinit var btn_image : Button
    lateinit var btn_gallery : Button

    //-get image from camera
    lateinit var imageUri: Uri
    private val contract = registerForActivityResult(ActivityResultContracts.TakePicture()){
        iv_profile.setImageURI(null)
        iv_profile.setImageURI(imageUri)
    }
    //-get image from gallery
    private val contracts = registerForActivityResult(ActivityResultContracts.GetContent()){
        iv_profile.setImageURI(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_demo = findViewById(R.id.tv_demo)
        iv_profile = findViewById(R.id.iv_profile)
        btn_image = findViewById(R.id.btn_image)
        btn_gallery = findViewById(R.id.btn_gallery)

        val quoteService = RetrofitHelper.getInstance().create(QuoteService::class.java)

        val repository = QuoteRepository(quoteService)

        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)

        mainViewModel.quotes.observe(this, Observer {
            //Log.d("Akash", it.results.toString())
            it.results.forEach {
                Log.d("Author", it.author)
                tv_demo.text = it.author
            }
            it.results.get(0).author
        })

        mainViewModel.authors.observe(this, Observer {
            Log.d("Authors", it.results.toString())
        })

        mainViewModel.quotesTags.observe(this, Observer {
            Log.d("Quotes Tags", it.results.toString())
        })

        //--------------------------------------------
        //Access camera
        imageUri = createImageUri()
        btn_image.setOnClickListener {
            contract.launch(imageUri)
        }

        //Access Gallery
        btn_gallery.setOnClickListener {
            contracts.launch("image/*")
        }

    }

    private fun createImageUri(): Uri{
        val image = File(applicationContext.filesDir, "camera_photo.png")
        return FileProvider.getUriForFile(applicationContext,
            "com.jetpackcomponents.mvvmwithretrofit.fileProvider",
            image)
    }


}