package com.cs364.fishquiz.ui.main

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cs364.fishquiz.R
import com.cs364.fishquiz.ui.data.Fish

@Composable
fun CatalogScreen(
    vmData: FishDBViewModel
) {
    val currFish by vmData.getAllFish().collectAsState(initial = listOf())

    LazyColumn(modifier = Modifier) {
        //items(currFish) { fish ->
        //    FishCard(fish = fish)
        //}
    }

    //currFish?.let { Text(it.) }
    //background image
    /*
    Image(
        painter = painterResource(id = R.drawable.image1),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .height(160.dp),
        contentScale = ContentScale.FillWidth
    )

     */
}

@Composable
fun FishCard(fish: Fish, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        elevation = 4.dp,
        modifier = modifier.padding(8.dp)
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
                Text(fish.common_name)
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
                //DogHobby(dog.hobbies)
            }
        }
    }
}

