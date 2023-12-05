package com.imageuploadretrofit.uploadimageexample

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {

    lateinit var imgView: ImageView
    lateinit var btnchange: Button
    lateinit var btnupload: Button
    lateinit var imageUri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setup()
    }

    fun setup() {
        imgView = findViewById(R.id.imgView)
        btnchange = findViewById(R.id.btnchange)
        btnupload = findViewById(R.id.btnupload)
        btnchange.setOnClickListener {
            getContent.launch("image/*")
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
        }
        btnupload.setOnClickListener {
            upload()
        }
    }

    var getContent = registerForActivityResult(ActivityResultContracts.GetContent()) {
        if (it != null) {
            imageUri = it
            imgView.setImageURI(it)
        }
    }

//    val contract = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
//    }

    fun upload() {
        //1.app ka anadr ak file create karni hai.
        val fileDir = applicationContext.filesDir //file directory access
        val file = File(fileDir, "image.png") //New file create and file name(image.png), File() ka object create karta hai
        //Now file created, jo ki app ki private directory ka anadr hogi.

        //2.Selected image, uska jo content hai copy kar langa File ka andar.
        var inputStream = contentResolver.openInputStream(imageUri) //user selected image uri
        val outputStream = FileOutputStream(file) //object banaya
        if (inputStream != null) {
            inputStream.copyTo(outputStream)
        }
        //Now we get file access

        /*Question - Why we are not able to directly access (36 line - it) ?
        * image user na select kari thi jo ki gallery mai padi hai, usko kyu nahi access kar sakta hai hum.
        * Ans: Security Practice, Apki App jo hai apna hi content access kar sakti hai.
        * Agar usko Dusari App ka content access karna hai, toh usko PERMISSION lani padagi.*/

        //3. Retrofit code
        val requestBody = file.asRequestBody("image/*".toMediaTypeOrNull()) //requestBody object, kis tarika ki file upload karna wala hai.
        val part = MultipartBody.Part.createFormData("profile", file.name, requestBody) //Multipart Object

        val retrofit =
            Retrofit.Builder().baseUrl("https://image-upload-api-retrofit.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(UploadService::class.java)

        CoroutineScope(Dispatchers.IO).launch { //bec. suspend function
            val response = retrofit.uploadImage(part)
            Log.d("Demo", response.toString())
        }

    }

}