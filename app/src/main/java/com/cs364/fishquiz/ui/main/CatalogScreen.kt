package com.cs364.fishquiz.ui.main

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
import com.cs364.fishquiz.ui.data.Habitat

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
                val fishHab by vmData.getHabitatFromId(fish.hab_id).collectAsState(initial = null)
                FishCard(fish, fishHab)
            }
        }
    }
}

/**
 * Composable for a single card shown on the screen.
 *
 * @param fish data container for the data we are displaying on this card
 * @param habitat the Habitat that the fish lives in
 * @param modifier modifiers applied to this composable
 */
@Composable
fun FishCard(
    fish: Fish,
    habitat: Habitat?,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        elevation = 4.dp,
        modifier = modifier.padding(8.dp),
        backgroundColor = Color.LightGray.copy(alpha = 0.9f)
    ) {
        //ordered in a column
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
            if (expanded) { //information to display when the card is expanded
                FishExpanded(fish = fish, habitat)
            }
        }
    }
}

/**
 * Composable for the expanding icon on the rightside of the card
 *
 * @param expanded Boolean showing what state the card is in. True if exanded, false otherwise
 * @param onClick Lambda function for what happens when this icon is clicked
 * @param modifier any Modifiers we want to apply to this composable
 */
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

/**
 * Composable used to display information about a fish when the card is expanded and not expanded
 *
 * @param fish the Fish we're displaying information about
 * @param modifier any Modifiers we want to apply to this composable
 */
@Composable
fun FishTitle(
    fish: Fish,
    modifier: Modifier = Modifier
) {
    Column {
        Text(   //name
            text =  fish.common_name,
            style = MaterialTheme.typography.h5,
            modifier = modifier.padding(top = 8.dp)
        )
        Text(   //scientific name
            text = fish.species,
            style = MaterialTheme.typography.body1
        )
    }
}

/**
 * Composable used to display infomation about the fish when the card is expanded
 *
 * @param fish the Fish we're displaying information about
 * @param habitat the habitat that the fish lives in
 * @param modifier and Modifiers we want to apply to this composable
 */
@Composable
fun FishExpanded(
    fish: Fish,
    habitat: Habitat?,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(
            start = 16.dp,
            top = 8.dp,
            bottom = 16.dp,
            end = 16.dp
        )
    ) {
        //display the description
        Divider()
        Text(
            text = fish.desc,
            style = MaterialTheme.typography.body1
        )
        Spacer(Modifier.weight(1f))
        Divider()
        //display the specifics
        Text(
            text = "Average Weight: " + fish.avg_weight_kg + " kg",
            style = MaterialTheme.typography.body2
        )
        Text(
            text = "Average Length: " + fish.avg_len_met + " m",
            style = MaterialTheme.typography.body2
        )
        Text(
            text = "Water Depth: " + fish.water_depth_met + "m",
            style = MaterialTheme.typography.body2
        )
        Text(
            text = "Habitat: " + habitat?.name,
            style = MaterialTheme.typography.body2
        )
        Divider()
    }
}