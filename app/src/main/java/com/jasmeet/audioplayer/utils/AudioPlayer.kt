package com.jasmeet.audioplayer.utils

import android.media.MediaPlayer
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf

import androidx.compose.runtime.mutableStateOf

object AudioPlayer {
    private val mediaPlayer = MediaPlayer()
    private val activeAudioUrl: MutableState<String?> = mutableStateOf(null)
    private val isAudioPlaying = mutableStateOf(false)
    // val audioDuration = mutableIntStateOf(0)



    init {
        mediaPlayer.setOnCompletionListener {
            isAudioPlaying.value = false
        }
//        mediaPlayer.setOnPreparedListener {
//            audioDuration.intValue = mediaPlayer.duration
//        }
    }

    fun togglePlayback(url: String) {
        if (url == activeAudioUrl.value) {
            if (isAudioPlaying.value) {
                mediaPlayer.pause()
                isAudioPlaying.value = false
                Log.d("AudioPlayer", "Toggling playback for active audio: $url")
            } else {
                mediaPlayer.start()

                isAudioPlaying.value = true
            }
        } else {
            activeAudioUrl.value?.let {
                mediaPlayer.stop()
                isAudioPlaying.value = false
                Log.d("AudioPlayer", "Starting new playback: $url")
            }

            mediaPlayer.reset()

            try {
                mediaPlayer.apply {

                    setDataSource(url)
                    prepareAsync()


                    setOnPreparedListener {
                        activeAudioUrl.value = url
                        start()
                        isAudioPlaying.value = true
                        //audioDuration.intValue = mediaPlayer.duration

                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun isAudioPlayingForUrl(url: String): Boolean {
        return activeAudioUrl.value == url && isAudioPlaying.value
    }

    fun release() {
        mediaPlayer.release()
    }
}
