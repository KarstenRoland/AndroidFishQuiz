package com.cs364.fishquiz

import androidx.activity.ComponentActivity
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.cs364.fishquiz.ui.data.Fish
import com.cs364.fishquiz.ui.main.CatalogScreen
import com.cs364.fishquiz.ui.main.FishCard
import com.cs364.fishquiz.ui.main.FishDBViewModel
import org.junit.Rule
import org.junit.Test


class CatalogTests {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun cardDisplayCatalog() {
        var fishList: List<Fish> = emptyList()

        composeRule.setContent {
            var myContext = LocalContext.current
            var vm: FishDBViewModel by remember { mutableStateOf(FishDBViewModel(myContext)) }
            val listF by vm.getAllFish().collectAsState(initial = emptyList())
            fishList = listF

        }

    }



}