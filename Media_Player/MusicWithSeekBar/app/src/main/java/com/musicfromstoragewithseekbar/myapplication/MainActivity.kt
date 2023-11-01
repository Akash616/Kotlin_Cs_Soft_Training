package com.musicfromstoragewithseekbar.myapplication

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.SeekBar
import android.widget.Toast
import com.musicfromstoragewithseekbar.myapplication.databinding.ActivityMainBinding
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mediaplayer: MediaPlayer
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        mediaplayer = MediaPlayer.create(this, R.raw.sri_keishna)

        binding.seekbar.progress = 0
        binding.seekbar.max = mediaplayer.duration

        binding.button.setOnClickListener {

            val endTime = mediaplayer.duration.toDouble()/1000
            //val twoDec = String.format("%.2f",endTime).replace(".",":")
            binding.tvEndTime.text = String.format("%.2f",endTime)

            if (!mediaplayer.isPlaying) {
                mediaplayer.start()
                binding.button.setBackgroundResource(R.drawable.pause)
            } else {
                mediaplayer.pause()
                binding.button.setBackgroundResource(R.drawable.play_arrow)
            }
        }

        val updateSeekBar = object : Runnable{
            override fun run() {
                val startTime = mediaplayer.currentPosition.toDouble()/1000
                //val twoDec = String.format("%.2f", startTime).replace(".",":")
                binding.tvStartTimer.text = String.format("%.2f", startTime)
                binding.seekbar.progress = mediaplayer.currentPosition
                handler.postDelayed(this, 1000) //update every second
            }
        }
        handler.postDelayed(updateSeekBar, 0) //start updating the seekbar

        mediaplayer.setOnCompletionListener {
            mediaplayer.pause()
            binding.button.setBackgroundResource(R.drawable.play_arrow)
        }

        //If you need to update the MediaPlayer's position while user drag your SeekBar
        //you should add OnSeekBarChangeListener to your SeekBar
        binding.seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser){
                    mediaplayer.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

    }



}