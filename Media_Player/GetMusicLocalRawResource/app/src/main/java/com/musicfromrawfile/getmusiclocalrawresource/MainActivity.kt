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

//private void createMediaPlayerIfNeeded() {
//    if (mMediaPlayer == null) {
//        mMediaPlayer = new MediaPlayer();
//
//        // Make sure the media player will acquire a wake-lock while
//        // playing. If we don't do that, the CPU might go to sleep while the
//        // song is playing, causing playback to stop.
//        mMediaPlayer.setWakeMode(mService.getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
//
//        // we want the media player to notify us when it's ready preparing,
//        // and when it's done playing:
//        mMediaPlayer.setOnPreparedListener(this);
//        mMediaPlayer.setOnCompletionListener(this);
//        mMediaPlayer.setOnErrorListener(this);
//        mMediaPlayer.setOnSeekCompleteListener(this);
//    } else {
//        mMediaPlayer.reset();
//    }
//}