package com.cs364.fishquiz.data

import android.content.Context

interface FishContainer {
    val fishRepo: FishRepository
}

class fishDataContainer(private val context: Context) : FishContainer {
    override val fishRepo: FishRepository by lazy {
        FishRepository(FishDatabase.getInstance(context).fishDao())
    }
}