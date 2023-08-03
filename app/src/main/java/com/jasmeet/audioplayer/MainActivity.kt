package com.jasmeet.audioplayer

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jasmeet.audioplayer.ui.theme.AudioPlayerTheme
import com.jasmeet.audioplayer.viewModel.AudioViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AudioPlayerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewmodel = hiltViewModel<AudioViewModel>()
                    AudioPlayerList(audioUrls,viewmodel)
                }

            }
        }
    }
}

val audioUrls = listOf(
    "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3",
    "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-2.mp3",
    "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-3.mp3",
    "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-4.mp3",
    "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-5.mp3",
    "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-6.mp3",

    )

@Composable
fun AudioPlayerScreen(
    audioUrl: String,
    audioPlayerViewModel: AudioViewModel
) {

    val isAudioPlaying = audioPlayerViewModel.isAudioPlayingForUrl(audioUrl)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        IconButton(
            onClick = {
                audioPlayerViewModel.togglePlayback(url = audioUrl)
                Log.d("Player", "AudioPlayerScreen:  isAudioPlaying: $isAudioPlaying")
            }
        ) {
            val icon = if (isAudioPlaying)
                Icons.Default.Pause
            else
                Icons.Default.PlayArrow

            Icon(
                imageVector = icon,
                contentDescription = "Play/Pause",
                modifier =Modifier.background(Color.Blue, CircleShape).padding(5.dp)
            )
        }
    }
}


@Composable
fun AudioPlayerList(audioUrls: List<String>,viewModel:AudioViewModel) {
    LazyColumn {
        items(audioUrls) { audioUrl ->
            AudioPlayerScreen(audioUrl = audioUrl, audioPlayerViewModel = viewModel)
        }
    }
}




