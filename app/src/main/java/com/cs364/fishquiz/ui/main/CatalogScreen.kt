package com.cs364.fishquiz.ui.main

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.cs364.fishquiz.ui.data.Fish

/**
 * Composable used to create the screens that display the fish catalogs.
 *
 * @param vmData viewModel that connects this UI composable to the database. Allows us to get data.
 * @param habitatToDisplay String that tells us which habitat to display on this page.
 * @param backgroundImageResource Int that tells us which image to display in the background
 */
@Composable
fun CatalogScreen(
    vmData: FishDBViewModel,
    habitatToDisplay: Int,
    backgroundImageResource: Int
) {
    //get every fish as a List<Fish>
    val currFish by vmData.getAllFishInHabitat(stringResource(habitatToDisplay)).collectAsState(initial = listOf())
    val emptyFishList: List<Fish> = listOf()
    val allFish by vmData.getAllFish().collectAsState(initial = emptyFishList)
    Log.d("i'm done being nice", allFish.size)

    //order screen in a box to overlay the image with the fishlist
    Box {
        Image(
            painter = painterResource(id = backgroundImageResource),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        LazyColumn(
            contentPadding = PaddingValues(bottom = 55.dp),
            modifier = Modifier.background(Color.Transparent)
        ) {
            items(currFish) { fish ->
                FishCard(fish)
            }
        }
    }
}

/**
 * Composable for a single card shown on the screen.
 *
 * @param fish data container for the data we are displaying on this card
 * @param modifier modifiers applied to this composable
 */
@Composable
fun FishCard(
    fish: Fish, modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        elevation = 4.dp,
        modifier = modifier.padding(8.dp),
        backgroundColor = Color.LightGray.copy(alpha = 0.9f)
    ) {
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioLowBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) { //information displayed when the card is in either state (expanded)
                FishTitle(fish = fish)
                Spacer(Modifier.weight(1f))
                ExpandIcon(
                    expanded = expanded,
                    onClick = { expanded = !expanded }
                )
            }
            if (expanded) { //infromation to display when the card is expanded
                FishExpanded(fish = fish)
            }
        }
    }
}

@Composable
fun ExpandIcon(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = if (expanded) Icons.Filled.KeyboardArrowDown else Icons.Filled.KeyboardArrowUp,
            tint = Color.Black,
            contentDescription = null,
        )
    }
}

@Composable
fun FishTitle(fish: Fish, modifier: Modifier = Modifier) {
    Column {
        Text(
            text =  fish.common_name,
            style = MaterialTheme.typography.h5,
            modifier = modifier.padding(top = 8.dp)
        )
        Text(
            text = fish.species,
            style = MaterialTheme.typography.body1
        )
    }
}

@Composable
fun FishExpanded(fish: Fish, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(
            start = 16.dp,
            top = 8.dp,
            bottom = 16.dp,
            end = 16.dp
        )
    ) {
        Text(
            text = fish.desc,
            style = MaterialTheme.typography.body1
        )
        Spacer(Modifier.weight(1f))
        Text(
            text = "Average Weight: " + fish.avg_weight_kg + " ",
            style = MaterialTheme.typography.body2
        )
    }
}