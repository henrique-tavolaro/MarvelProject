package com.example.marvelproject.ui.composables

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavController
import com.example.marvelproject.DEBUG_TAG
import com.example.marvelproject.DEFAULT_CIRCLE_IMAGE
import com.example.marvelproject.model.Result
import com.example.marvelproject.ui.fragments.HomeFragmentDirections
import com.example.marvelproject.ui.theme.appFontFamily
import com.google.gson.Gson

@Composable
fun LazyColumnItemTile(
    result: Result,
    onClick: () -> Unit
){

    Row(
        modifier = Modifier
            .padding(vertical = 18.dp, horizontal = 24.dp)
            .fillMaxWidth()
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        result.thumbnail.let {
            val url ="${it.path}.${it.extension}"
            val image =
                loadImageUri(
                    url = url.toUri(),
                    defaultImage = DEFAULT_CIRCLE_IMAGE
                ).value
            image?.let { img ->
                Image(
                    bitmap = img.asImageBitmap(),
                    modifier = Modifier
                        .height(80.dp)
                        .width(80.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
            }
        }
            Text(
                modifier = Modifier.padding(start = 18.dp),
                text = result.name,
                fontSize = 20.sp,
                fontFamily = appFontFamily,
                fontWeight = FontWeight.Normal,
                color = Color.Black
            )

    }



}