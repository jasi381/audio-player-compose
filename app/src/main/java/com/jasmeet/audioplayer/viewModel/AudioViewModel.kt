package com.jasmeet.audioplayer.viewModel

import android.media.MediaPlayer
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class AudioViewModel @Inject constructor() : ViewModel() {
    private val mediaPlayer = MediaPlayer()

    private val activeAudioUrl: MutableState<String?> = mutableStateOf(null)

    val isLoading = mutableStateOf(false)
    private val isAudioPlaying = mutableStateOf(false)

    init {
        mediaPlayer.setOnCompletionListener {
            isAudioPlaying.value = false
        }
    }

    fun togglePlayback(url: String) {
        if (url == activeAudioUrl.value) {
            if (isAudioPlaying.value) {
                mediaPlayer.pause()
                isAudioPlaying.value = false
                Log.d("AudioViewModel", "Toggling playback for active audio: $url")
            } else {
                mediaPlayer.start()
                isAudioPlaying.value = true
            }
        } else {
            activeAudioUrl.value?.let {
                mediaPlayer.stop()
                isAudioPlaying.value = false
                Log.d("AudioViewModel", "Starting new playback: $url")
            }

            mediaPlayer.reset()

            try {
                mediaPlayer.apply {
                    setDataSource(url)
                    isLoading.value = true
                    prepareAsync()

                    setOnPreparedListener {
                        activeAudioUrl.value = url
                        isLoading.value = false
                        start()
                        isAudioPlaying.value = true
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                isLoading.value = false
            }
        }
    }
    fun isAudioPlayingForUrl(url: String): Boolean {
        return activeAudioUrl.value == url && isAudioPlaying.value
    }




    override fun onCleared() {
        mediaPlayer.release()
        super.onCleared()
    }
}

