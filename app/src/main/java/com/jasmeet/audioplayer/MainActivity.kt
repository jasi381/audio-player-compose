package com.jasmeet.audioplayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.jasmeet.audioplayer.components.AppBarComponent
import com.jasmeet.audioplayer.data.AudioData
import com.jasmeet.audioplayer.data.data
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
                    modifier = Modifier.fillMaxSize().background(Color(0xff0e1d41)),
                    color =Color.Black
                ) {
                    MainLayout(data = data)
                }

            }
        }
    }
}



@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun AudioPlayerScreen(
    audioPlayerViewModel: AudioViewModel,
    audioUrl: String,
    imageUrl:String,
    body:String,
    duration:String,
    modifier: Modifier = Modifier,
    painter: Int?= R.drawable.icon_library


) {
    val isAudioPlaying = audioPlayerViewModel.isAudioPlayingForUrl(audioUrl)

    Row(
        modifier = modifier.padding(horizontal = 5.dp).background(Color(0xff07132D).copy(alpha = 0.2f), RoundedCornerShape(10.dp)),
        verticalAlignment = Alignment.CenterVertically

    ) {
        GlideImage(
            model = imageUrl,
            contentDescription = "content Image",
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp))
                .fillMaxWidth(0.47f)
                .height(101.dp),
            contentScale = ContentScale.FillBounds
        )
        Spacer(modifier = Modifier.width(5.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(98.dp)
                .padding(1.dp)
        ) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = body,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ){
                Row {
                    Image(
                        painter = painterResource(painter!!),
                        contentDescription = null,
                        modifier = Modifier
                            .background(Color(0xffcf0a2c), RoundedCornerShape(2.dp))
                            .height(16.dp)
                            .width(16.dp)
                            .padding(2.dp)

                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = duration, color = Color.White)

                }

                IconButton(
                    onClick = {
                        audioPlayerViewModel.togglePlayback(url = audioUrl)
                    }
                ) {
                    val icon = if (isAudioPlaying)
                        Icons.Default.Pause
                    else
                        Icons.Default.PlayArrow

                    Icon(
                        imageVector = icon,
                        contentDescription = "Play/Pause",
                        modifier = Modifier
                            .background(Color(0xffc2c6cf), CircleShape)
                            .padding(5.dp)
                            .size(24.dp),

                        )

                    //if loading show progress bar
//                    if (audioPlayerViewModel.isLoading.value){
//                        CircularProgressIndicator(
//                            modifier = Modifier
//                                .background(Color(0xffc2c6cf), CircleShape)
//                                .padding(5.dp)
//                                .size(24.dp)
//                        )
//                    }
                }

            }
            Spacer(modifier = Modifier.height(4.dp))



        }

    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainLayout(
    data:List<AudioData>,

    ) {
    Scaffold(
        topBar = {
            AppBarComponent(
                title = "Playlists",
                onBackClick = {
                    //TODO: Handle back click
                }
            )
        }
    ) { paddingValues->
        LazyColumn(
            modifier = Modifier
                .padding(
                    top = paddingValues.calculateTopPadding(),
                )
                .fillMaxSize()
                .background(Color(0xff07132D))
        ){
            items(data){data->
                AudioPlayerScreen(
                    audioPlayerViewModel = hiltViewModel(),
                    audioUrl =data.audioUrl,
                    imageUrl = data.imageUrl,
                    body =data.body ,
                    duration = data.duration,
                    modifier = Modifier.padding(horizontal = 8.dp)

                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }


    }

}




