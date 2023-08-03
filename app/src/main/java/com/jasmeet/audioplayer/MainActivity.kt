package com.jasmeet.audioplayer

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jasmeet.audioplayer.ui.theme.AudioPlayerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AudioPlayerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    customToastUI()
                }
            }
        }
    }
}



@Composable
fun customToastUI() {

    val ctx = LocalContext.current
    val mediaPlayer = remember { MediaPlayer() }
    val isLoading = remember { mutableStateOf(false) }
    val isPlaying = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Play Audio from URL",
            fontWeight = FontWeight.Bold,
            color = Color.Green,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(20.dp))

        IconButton(
            onClick = {
                val audioUrl = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"
                if (!isPlaying.value) {
                    if (!mediaPlayer.isPlaying) {
                        isLoading.value = true
                        mediaPlayer.reset()

                        try {
                            mediaPlayer.setDataSource(audioUrl)
                            mediaPlayer.prepareAsync()

                            mediaPlayer.setOnPreparedListener {
                                mediaPlayer.start()
                                isLoading.value = false
                                isPlaying.value = true
                                Toast.makeText(ctx, "Audio started playing..", Toast.LENGTH_SHORT).show()
                            }

                        } catch (e: Exception) {
                            e.printStackTrace()
                            isLoading.value = false
                        }
                    }
                } else {
                    mediaPlayer.pause()
                    isPlaying.value = false
                    Toast.makeText(ctx, "Audio paused..", Toast.LENGTH_SHORT).show()
                }
            }
        ) {
            val icon = if (isLoading.value || isPlaying.value) Icons.Default.Pause else Icons.Default.PlayArrow
            Icon(imageVector = icon, contentDescription = "Play/Pause")
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}



