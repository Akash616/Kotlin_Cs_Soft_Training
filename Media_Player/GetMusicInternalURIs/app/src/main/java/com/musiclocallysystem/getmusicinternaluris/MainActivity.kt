package com.musiclocallysystem.getmusicinternaluris

import android.content.ContentResolver
import android.content.pm.PackageManager
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    //https://developer.android.com/guide/topics/media/platform/mediaplayer#kotlin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val contentResolver: ContentResolver = applicationContext.contentResolver
        val myUri: Uri =  Uri.parse("content://media/external/audio/media/123")
        val audioPlayer = AudioPlayer(applicationContext)
        audioPlayer.playAudioFromUri(myUri)

    }



}