package com.musicfromstoragewithseekbar.myapplication

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.SeekBar
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity2 : AppCompatActivity() {

    lateinit var fbtnPlay: FloatingActionButton
    lateinit var fbtnPause: FloatingActionButton
    lateinit var fbtnStop: FloatingActionButton

    lateinit var seekBar: SeekBar

    private var currentSong = mutableListOf(
        R.raw.karma_song,
        R.raw.sri_keishna
    )

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        fbtnPlay = findViewById(R.id.fbtnPlay)
        fbtnPause = findViewById(R.id.fbtnPause)
        fbtnStop = findViewById(R.id.fbtnStop)
        seekBar = findViewById(R.id.seekBar)

        controlSong(currentSong[1])

    }

    private fun controlSong(id: Int) {

        fbtnPlay.setOnClickListener {
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(this, id)
            }
            mediaPlayer?.start()
            initSeekBar()
        }

        fbtnPause.setOnClickListener {
            if (mediaPlayer != null) {
                mediaPlayer?.pause()
            }
        }

        fbtnStop.setOnClickListener {
            if (mediaPlayer != null) {
                mediaPlayer?.stop()
                mediaPlayer?.reset()
                mediaPlayer?.release()
                mediaPlayer = null
            }
        }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser){
                    mediaPlayer!!.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}

        })

    }

    private fun initSeekBar() {
        seekBar.progress = mediaPlayer!!.duration

        val handler = Handler()
        handler.postDelayed(object : Runnable{
            override fun run() {
                try {
                    seekBar.progress = mediaPlayer!!.currentPosition
                    handler.postDelayed(this, 1000)
                }catch (e : Exception){
                    seekBar.progress = 0
                }
            }

        }, 0)

    }


}