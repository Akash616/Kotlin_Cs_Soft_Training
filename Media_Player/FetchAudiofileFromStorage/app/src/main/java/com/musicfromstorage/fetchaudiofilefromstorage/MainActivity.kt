package com.musicfromstorage.fetchaudiofilefromstorage

import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts

//https://www.tothenew.com/blog/android-katha-onactivityresult-is-deprecated-now-what/
//https://stackoverflow.com/questions/62671106/onactivityresult-method-is-deprecated-what-is-the-alternative
//https://www.geeksforgeeks.org/how-to-fetch-audio-file-from-storage-in-android/

class MainActivity : AppCompatActivity() {

    lateinit var select_Audio: TextView
    lateinit var select_Image: TextView
    lateinit var btn_pause: Button
    lateinit var btn_play: Button
    lateinit var btn_stop: Button
    lateinit var iv_one: ImageView

    //val PICK_AUDIO = 1;
    lateinit var AudioUri: Uri
    var mediaPlayer = MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        select_Audio = findViewById(R.id.select_Audio)
        iv_one = findViewById(R.id.iv_one)
        select_Image = findViewById(R.id.select_Image)
        btn_pause = findViewById(R.id.btn_pause)
        btn_play = findViewById(R.id.btn_play)
        btn_stop = findViewById(R.id.btn_stop)

        select_Audio.setOnClickListener {
            val audioIntent = Intent()
            audioIntent.setType("audio/*") //MIME Type - "audio/*"
            audioIntent.setAction(Intent.ACTION_OPEN_DOCUMENT)
            //startActivityForResult(Intent.createChooser(audioIntent, "Select Audio"), PICK_AUDIO) //deprected but working
            //Now - ActivityResultLauncher
            activityResultLauncher.launch(audioIntent)
        }

        select_Image.setOnClickListener {
            getContent.launch("image/*")
            //getContent.launch("audio/*") working
        }

    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == PICK_AUDIO && resultCode == RESULT_OK){
//            if (data != null) {
//                // Audio is Picked in format of URI
//                //AudioUri = data.getData()
//                AudioUri = data.data!!
//                select_Audio.text = "Audio Selected"
//            }
//        }
//    }

    var activityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {

            if (it.resultCode == RESULT_OK && it != null) {
                mediaPlayer.release() //audio changes

                var data: Intent? = it.data
                AudioUri = data?.data!!
                select_Audio.text = "Audio Selected"

                //Music Player
                val myUri: Uri = AudioUri
                mediaPlayer = MediaPlayer().apply {
                    setAudioAttributes(
                        AudioAttributes.Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .setUsage(AudioAttributes.USAGE_MEDIA)
                            .build()
                    )
                    setDataSource(applicationContext, myUri)
                    prepare()
                    //start()
                }

                btn_pause.setOnClickListener {
                    mediaPlayer.pause()
                }

                btn_play.setOnClickListener {
                    mediaPlayer.start()
                }

                btn_stop.setOnClickListener {
                    mediaPlayer.stop()
                    mediaPlayer.prepare()
                }

            }

        }

    var getContent = registerForActivityResult(ActivityResultContracts.GetContent()) {
        if (it != null) {
            iv_one.setImageURI(it)
            select_Image.text = "Image Selected"
        }
    }


}