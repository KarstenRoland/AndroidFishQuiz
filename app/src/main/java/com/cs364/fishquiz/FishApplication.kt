package com.cs364.fishquiz

import android.app.Application
import com.cs364.fishquiz.ui.data.FishContainer
import com.cs364.fishquiz.ui.data.fishDataContainer

class FishApplication: Application() {
    /**
     * AppContainer instance used by the rest of the classes to obtain dependencies.
     * The 'lateinit' keyword is used to declare a non-null type that will be initialized later.
     */
    lateinit var container: FishContainer

    /**
     * The onCreate() method is called when the application is starting up.
     * It is used to initialize the AppContainer instance with dependencies.
     */
    override fun onCreate() {
        super.onCreate()
        container = fishDataContainer(this)
    }
}
