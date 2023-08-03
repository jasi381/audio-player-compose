package com.jasmeet.audioplayer.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jasmeet.audioplayer.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarComponent(title:String, onBackClick:()->Unit) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = TextStyle(
                    color = Color.White,
                    fontSize = 18.sp
                ),
                textAlign = TextAlign.Center
            )
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    onBackClick.invoke()
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "back",
                    modifier = Modifier
                        .padding(5.dp),
                    tint = Color.White
                )
            }
        },
        modifier = Modifier
            .fillMaxWidth(),
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color(0xff0D1D41)
        )
    )
}



@Preview
@Composable
fun AppBarComponentPreview() {

    AppBarComponent(title = "Playlists", onBackClick = {})

}
