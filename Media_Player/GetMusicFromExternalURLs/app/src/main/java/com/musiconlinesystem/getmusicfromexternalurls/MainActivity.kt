package com.musiconlinesystem.getmusicfromexternalurls

import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.musiconlinesystem.getmusicfromexternalurls.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val url = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"
        val mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setDataSource(url)
            prepare() // might take long! (for buffering, etc)
            start()
        }

        binding.btnPlay.setOnClickListener {
            mediaPlayer.start()
        }

        binding.btnPause.setOnClickListener {
            mediaPlayer.pause()
        }

        binding.btnStop.setOnClickListener {
            mediaPlayer.stop()
            mediaPlayer.prepare()
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