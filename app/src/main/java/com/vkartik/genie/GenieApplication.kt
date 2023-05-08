package com.vkartik.genie

import android.app.Application
import com.vkartik.genie.data.AppContainer
import com.vkartik.genie.data.AppDataContainer

class GenieApplication: Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}