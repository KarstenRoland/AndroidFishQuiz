package com.cs364.fishquiz.ui

import com.cs364.fishquiz.ui.main.FishDBViewModel
import android.app.Application
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.cs364.fishquiz.FishApplication

/**
 * Provides Factory to create instance of ViewModel for the entire Inventory app
 */
object ViewModelFactory {
    val Factory = viewModelFactory {
        // Initializer for FishDBViewModel
        initializer {
            FishDBViewModel(fishApplication().container.fishRepo)
        }
    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [InventoryApplication].
 */
fun CreationExtras.fishApplication(): FishApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as FishApplication)
