package com.cs364.fishquiz

import android.os.Bundle
import android.support.test.rule.ActivityTestRule
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.cs364.fishquiz.ui.main.PlaceholderFragment
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class PlaceholderFragmentTest {

    // Required for LiveData to work in tests
    @get:Rule
    val mActivityTestRule:ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testDefaultImageDisplayed() {
        // Verify that the default image is displayed
        onView(withId(R.id.backgroundImage)).check(matches(isDisplayed()))
        onView(withId(R.id.backgroundImage)).check(matches(withId(R.drawable.default_image)))
    }

    @Test
    fun testImage1Displayed() {

        // Verify that image 1 is displayed
        onView(withId(R.id.compose_view)).check(matches(isDisplayed()))
        onView(withId(R.id.compose_view)).check(matches(hasDescendant(withId(R.drawable.image1))))
    }

    @Test
    fun testImage2Displayed() {

        // Verify that image 2 is displayed
        onView(withId(R.id.compose_view)).check(matches(isDisplayed()))
        onView(withId(R.id.compose_view)).check(matches(hasDescendant(withId(R.drawable.image2))))
    }

    @Test
    fun testImage3Displayed() {

        // Verify that image 3 is displayed
        onView(withId(R.id.compose_view)).check(matches(isDisplayed()))
        onView(withId(R.id.compose_view)).check(matches(hasDescendant(withId(R.drawable.image3))))
    }


    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"
    }
}

