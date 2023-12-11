package com.kotlinpratice.memeshareapp

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.kotlinpratice.memeshareapp.modal.RandomMeme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class MainActivity : AppCompatActivity() {

    lateinit var apiInterface: ApiInterface
    lateinit var randomMeme: RandomMeme
    lateinit var idLoadingPB: ProgressBar
    lateinit var ivMemeImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        idLoadingPB = findViewById(R.id.idLoadingPB)
        ivMemeImage = findViewById(R.id.ivMemeImage)

        apiCall()

    }

    private fun apiCall() {
        idLoadingPB.visibility = View.VISIBLE
        apiInterface = RetrofitHelper.getInstance().create(ApiInterface::class.java)
        val call: Call<RandomMeme> = apiInterface.getRandomMeme()
        call.enqueue(object : Callback<RandomMeme>{
            override fun onResponse(call: Call<RandomMeme>, response: Response<RandomMeme>) {
                if (response.isSuccessful){
                    idLoadingPB.visibility = View.GONE
                    randomMeme = response.body()!!
                    Glide.with(applicationContext).load(randomMeme.url).listener(object: RequestListener<Drawable>{
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>,
                            isFirstResource: Boolean
                        ): Boolean {
                            idLoadingPB.visibility = View.GONE
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable,
                            model: Any,
                            target: Target<Drawable>?,
                            dataSource: DataSource,
                            isFirstResource: Boolean
                        ): Boolean {
                            idLoadingPB.visibility = View.GONE
                            return false
                        }

                    }).into(ivMemeImage)
                }
            }

            override fun onFailure(call: Call<RandomMeme>, t: Throwable) {
                Toast.makeText(applicationContext, "onFailure: "+t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    fun shareMeme(view: View) {
        //intent - inter process communication in Android OS.
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, "Hey, Checkout this cool meme I got from Reddit ${randomMeme.url}")
        val chooser = Intent.createChooser(intent, "Share this meme using...")
        startActivity(chooser)
    }

    fun nextMeme(view: View) {
        apiCall()
    }

}