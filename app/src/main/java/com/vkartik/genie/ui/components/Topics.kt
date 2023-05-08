package com.vkartik.genie.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vkartik.genie.R

@Composable
fun Topic(imageUri: Uri = Uri.EMPTY, imgResId: Int, modifier: Modifier = Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
//        AsyncImage(
//            model = imgResId,
//            contentDescription = null,
//            alignment = Alignment.Center,
//            contentScale = ContentScale.Crop,
//            modifier = Modifier
//                .height(200.dp)
//                .width(200.dp).clip(CircleShape)
//        )
        Image(
            painter = painterResource(id = imgResId),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(88.dp).clip(CircleShape))
        Text(text = "Hello", Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp))
    }
}

@Composable
@Preview(uiMode = UI_MODE_NIGHT_YES)
fun TopicPreview() {
    Topic(imgResId = R.drawable.gifts, modifier = Modifier.background(color = MaterialTheme.colorScheme.secondaryContainer).padding(8.dp))
}