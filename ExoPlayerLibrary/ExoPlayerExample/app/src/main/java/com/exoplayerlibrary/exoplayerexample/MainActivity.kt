package com.exoplayerlibrary.exoplayerexample

import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.exoplayerlibrary.exoplayerexample.databinding.ActivityMainBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem

class MainActivity : AppCompatActivity() {

    //https://www.geeksforgeeks.org/exoplayer-in-android-with-example/
    //https://www.geeksforgeeks.org/android-exoplayer-using-kotlin/
    /*ExoPlayer is a media player library that provides a way to play audio and video
    with lots of customization in it. It is an alternative that is used to play videos
    and audios in Android along with MediaPlayer. ExoPlayerView is used for audio as
    well as video streaming in Android apps.*/

    private lateinit var binding: ActivityMainBinding
    lateinit var videoUri: Uri
    private var videoUrlFirst = "https://media.geeksforgeeks.org/wp-content/uploads/20201217163353/Screenrecorder-2020-12-17-16-32-03-350.mp4"
    private var videoUrlSecond = "https://media.geeksforgeeks.org/wp-content/uploads/20201217163353/Screenrecorder-2020-12-17-16-32-03-350.mp4"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val player = ExoPlayer.Builder(this).build() //ExoPlayer instance
        binding.playerView.player = player

        val videoUri : Uri = Uri.parse(videoUrlFirst)
        val firstMediaItem  = MediaItem.fromUri(videoUri)
        val secondMediaItem = MediaItem.fromUri(Uri.parse(videoUrlSecond))
        //player.setMediaItem(firstItem) // Set the media item to be played.
        player.addMediaItem(firstMediaItem) //multiple media items to be played one after the other
        player.addMediaItem(secondMediaItem)
        player.prepare()
        player.play()

//        binding.tvgetMusic.setOnClickListener {
//            val videoIntent = Intent()
//            videoIntent.setType("video/*")
//            videoIntent.setAction(Intent.ACTION_OPEN_DOCUMENT)
        // ACTION_OPEN_DOCUMENT - Allow the user to select and return one or more existing documents.
//            activityResultLauncher.launch(videoIntent)
//        }

    }

//    var activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
//        if (it.resultCode == RESULT_OK && it != null) {
//            //mediaPlayer.release() //audio changes
//
//            val data: Intent? = it.data
//            videoUri = data?.data!!
//            binding.tvgetMusic.text = "Video Selected"
//
//            //Video Player
//            val player = ExoPlayer.Builder(this).build() //ExoPlayer instance
//            binding.playerView.player = player
//
//            val firstItem  = MediaItem.fromUri(videoUri)
//            player.setMediaItem(firstItem)
//            player.prepare()
//            player.play()
//
//        }
//    }

}