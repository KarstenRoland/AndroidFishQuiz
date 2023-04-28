package com.cs364.fishquiz

import android.app.Application
import com.cs364.fishquiz.ui.data.FishContainer
import com.cs364.fishquiz.ui.data.fishDataContainer

class FishApplication: Application() {
    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: FishContainer
    override fun onCreate() {
        super.onCreate()
        container = fishDataContainer(this)
    }
}