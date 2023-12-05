package com.exoplayerlibrary.exoplayerexample

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.exoplayerlibrary.exoplayerexample.databinding.ActivityMain2Binding
import com.exoplayerlibrary.exoplayerexample.databinding.ActivityMainBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding
    private lateinit var videoUri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.textviewTwo.setOnClickListener {
            val videoIntent = Intent()
            videoIntent.setType("video/*")
            videoIntent.setAction(Intent.ACTION_OPEN_DOCUMENT)
            registerForResult.launch(videoIntent)
        }

    }

    val registerForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK && it != null) {
                val player = ExoPlayer.Builder(this).build()
                binding.playerViewTwo.player = player

                val data : Intent? = it.data
                if (data != null) {
                    videoUri = data.data!!
                }
                val mediaItem = MediaItem.fromUri(videoUri)
                player.setMediaItem(mediaItem)
                player.prepare()
                player.play()

            }
        }

}