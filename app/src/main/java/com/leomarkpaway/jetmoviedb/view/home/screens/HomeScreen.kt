package com.leomarkpaway.jetmoviedb.view.home.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.leomarkpaway.jetmoviedb.R
import com.leomarkpaway.jetmoviedb.ui.theme.extension.generateDominantColorState
import com.leomarkpaway.jetmoviedb.ui.theme.modifiers.verticalGradientBackground
import com.leomarkpaway.jetmoviedb.view.home.components.pager.MoviesPager
import com.leomarkpaway.jetmoviedb.intents.HomeInteractionEvents

@Composable
fun HomeScreen(moviesHomeInteractionEvents: (HomeInteractionEvents) -> Unit) {
    HomeScreenContent(moviesHomeInteractionEvents)
}

@Composable
fun HomeScreenContent(moviesHomeInteractionEvents: (HomeInteractionEvents) -> Unit) {
    val imageId = remember { mutableStateOf(R.drawable.camelia) }
    val context = LocalContext.current
    val defaultBitmap =
        ImageBitmap.imageResource(context.resources, imageId.value).asAndroidBitmap()
    val currentBitmap = remember { mutableStateOf(defaultBitmap) }
    val swatch = currentBitmap.value.generateDominantColorState()
    val dominantColors = listOf(Color(swatch.rgb), Color.Black)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .verticalGradientBackground(dominantColors),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item { Spacer(modifier = Modifier.height(30.dp)) }
        item {
            Text(
                text = "Now Showing",
                style = typography.h5.copy(fontWeight = FontWeight.ExtraBold),
                modifier = Modifier.padding(16.dp)
            )
        }
        item { MoviesPager(imageId, moviesHomeInteractionEvents) }
    }
}
