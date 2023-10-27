package com.musicfromrawfile.getmusiclocalrawresource

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.musicfromrawfile.getmusiclocalrawresource.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //https://developer.android.com/guide/topics/media/platform/mediaplayer#kotlin
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var mediaPlayer = MediaPlayer.create(this, R.raw.sri)

        binding.btnPause.setOnClickListener {
            mediaPlayer.pause()
        }

        binding.btnPlay.setOnClickListener {
            mediaPlayer.start()
        }

        binding.btnStop.setOnClickListener {
            // stop() method is used to completely
            // stop playing the mediaplayer instance
            mediaPlayer.stop()
            // after stopping the mediaplayer instance
            // it is again need to be prepared
            // for the next instance of playback
            mediaPlayer.prepare()

            //mediaPlayer.pause()
            //mediaPlayer.seekTo(0)
        }

        mediaPlayer.setOnCompletionListener {

        }

    }
}