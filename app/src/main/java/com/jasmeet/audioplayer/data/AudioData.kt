package com.jasmeet.audioplayer.data

import com.jasmeet.audioplayer.R

data class AudioData(
    val audioUrl: String,
    val imageUrl: String,
    val duration:String,
    val body :String,
    val painter: Int = R.drawable.icon_library,
)

val data = listOf(
    AudioData(
        audioUrl = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3",
        imageUrl = "https://lite-images-i.scdn.co/image/ab67656300005f1f4e6a763098014a0895fe6c08",
        duration = "5 MIN",
        body = "Podcast title goes here lorem ipsum dolor sit amet consectetur",

    ),
    AudioData(
        audioUrl = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-2.mp3",
        imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQT8QISOwyEwYlLX9mLh-aAkRO9xvY2rOQotgOQ-ktSotgZY6m5569Fm-KPTTpbldn6U94&usqp=CAU",
        duration = "5 MIN",
        body = "Podcast title goes here lorem ipsum dolor sit amet consectetur"
    ),
    AudioData(
        audioUrl = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-3.mp3",
        imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTy7_zsF7pTB8HUMHwFfbXUMVrvA0YddDmd_DZlAXwvS1nKn6BQX8zp-BW21BCAIl6DoAk&usqp=CAU",
        duration = "5 MIN",
        body = "Podcast title goes here lorem ipsum dolor sit amet consectetur"
    ),
    AudioData(
        audioUrl = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-4.mp3",
        imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRmhmAvbdfN3Qy2IJ9naGiRlyHoJZIVVAHVpTsrSwvFdfkHkecFG8O5SPuk2KqLgY4uKrQ&usqp=CAU",
        duration = "5 MIN",
        body = "Podcast title goes here lorem ipsum dolor sit amet consectetur"
    ),
    AudioData(
        audioUrl = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-5.mp3",
        imageUrl = "https://lite-images-i.scdn.co/image/ab67656300005f1f4e6a763098014a0895fe6c08",
        duration = "5 MIN",
        body = "Podcast title goes here lorem ipsum dolor sit amet consectetur"
    ),
    AudioData(
        audioUrl = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-6.mp3",
        imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRqEOhwP5C348avLA0m-BZjvxiOkfU5dpA09yY-lfzI4e1HDaLReRfsN-5pFZgFDCztGb8&usqp=CAU",
        duration = "5 MIN",
        body = "Podcast title goes here lorem ipsum dolor sit amet consectetur"
    ),

)
