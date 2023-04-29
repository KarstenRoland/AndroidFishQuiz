package com.cs364.fishquiz.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cs364.fishquiz.R

@Composable
fun CatalogScreen(
    vmData: FishDBViewModel
) {
    Column(modifier = Modifier.fillMaxSize()) {
        val currFish by vmData.getFishFromId(5).collectAsState(initial = null)
        currFish?.let { Text(it.common_name) }
        Image(
            painter = painterResource(id = R.drawable.image1),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .height(160.dp),
            contentScale = ContentScale.FillWidth
        )
    }
}
