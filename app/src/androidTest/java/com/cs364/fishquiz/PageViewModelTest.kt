package com.cs364.fishquiz

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cs364.fishquiz.ui.main.PageViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PageViewModelTest {

    // Required for LiveData to work in tests
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: PageViewModel

    @Before
    fun setup() {
        viewModel = PageViewModel()
    }

    @Test
    fun index0test() {
        viewModel.setIndex(0)
        assertEquals(null, viewModel.text.value)
    }

    @Test
    fun index1test() {
        viewModel.setIndex(1)
        assertEquals(null, viewModel.text.value)
    }
}
