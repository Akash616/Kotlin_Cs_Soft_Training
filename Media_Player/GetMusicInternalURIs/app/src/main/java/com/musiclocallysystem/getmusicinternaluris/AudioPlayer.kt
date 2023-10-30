package com.musiclocallysystem.getmusicinternaluris

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri

class AudioPlayer(private val context: Context) {

    fun playAudioFromUri(myUri: Uri) {
        val mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setDataSource(context, myUri)
            prepare()
            start()
        }
    }

}