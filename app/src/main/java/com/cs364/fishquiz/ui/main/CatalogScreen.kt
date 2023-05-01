package com.cs364.fishquiz.ui.main

import androidx.annotation.StringRes
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.cs364.fishquiz.R
import com.cs364.fishquiz.ui.data.Fish

@Composable
fun CatalogScreen(
    vmData: FishDBViewModel,
    habitatToDisplay: String,
    backgroundImageResource: Int
) {
    val currFish by vmData.getAllFishInHabitat(habitatToDisplay).collectAsState(initial = listOf())

    Box {
        Image(
            painter = painterResource(id = backgroundImageResource),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        LazyColumn(
            modifier = Modifier.background(Color.Transparent)
        ) {
            items(currFish) { fish ->
                FishCard(fish)
            }
        }
    }
}

@Composable
fun FishCard(
    fish: Fish, modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        elevation = 4.dp,
        modifier = modifier.padding(8.dp),
        backgroundColor = Color.LightGray.copy(alpha = 0.8f)
    ) {
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                FishInformation(fish = fish)
                Spacer(Modifier.weight(1f))
                ExpandIcon(
                    expanded = expanded,
                    onClick = { expanded = !expanded }
                )

                /*
                DogIcon(dog.imageResourceId)
                DogInformation(dog.name, dog.age)
                Spacer(Modifier.weight(1f))
                DogItemButton(
                    expanded = expanded,
                    onClick = { expanded = !expanded },
                )
                 */
            }
            if (expanded) {
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
                }
            }
        }
    }
}

@Composable
private fun ExpandIcon(
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
fun FishInformation(fish: Fish, modifier: Modifier = Modifier) {
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
