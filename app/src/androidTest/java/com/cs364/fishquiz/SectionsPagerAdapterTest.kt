package com.cs364.fishquiz

import android.content.Context
import android.content.res.Resources
import androidx.fragment.app.FragmentManager
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.cs364.fishquiz.ui.main.SectionsPagerAdapter
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations


@RunWith(AndroidJUnit4::class)
@SmallTest
class SectionsPagerAdapterTest {

    @Mock
    lateinit var mockFragmentManager: FragmentManager

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }


    //Test to see if title page matches get title page
    @Test
    fun testGetPageTitle() {
        // Create a mock context
        val mockContext = mock(Context::class.java)

        // Create a mock resources object
        val mockResources = mock(Resources::class.java)

        // Set up a mock resource string for the first tab title
        `when`(mockResources.getString(R.string.tab_text_1)).thenReturn("Tab 1")

        // Set up the mock context to return the mock resources object
        `when`(mockContext.resources).thenReturn(mockResources)

        // Create an instance of the adapter
        val adapter = SectionsPagerAdapter(mockContext, mockFragmentManager)

        // Check that the first tab title is correct
        assertEquals("Tab 1", adapter.getPageTitle(0))
    }




}