package com.cs364.fishquiz.ui.main

import androidx.lifecycle.*

/**
 * ViewModel class for a specific page of the app.
 * Holds the current page index and returns a text LiveData that describes the page.
 */
class PageViewModel : ViewModel() {

    // Private mutable LiveData that holds the current page index.
    private val _index = MutableLiveData<Int>()

    // Public LiveData that holds a string describing the current page.
    val text: LiveData<String> = _index.map {
        "Hello world from section: $it"
    }

    /**
     * Sets the current page index to the given value.
     */
    fun setIndex(index: Int) {
        _index.value = index
    }
}
