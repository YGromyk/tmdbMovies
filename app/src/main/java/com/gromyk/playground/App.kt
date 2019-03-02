package com.gromyk.playground

import android.app.Application
import com.gromyk.playground.di.viewModels
import com.instabug.library.Instabug
import com.instabug.library.invocation.InstabugInvocationEvent
import org.koin.android.ext.android.startKoin
import timber.log.Timber


class App : Application() {
class App: Application(){
    override fun onCreate() {
        super.onCreate()
        instance = this
        Timber.plant(Timber.DebugTree())
        Instabug.Builder(this, BuildConfig.INSTA_BUG)
            .setInvocationEvents(InstabugInvocationEvent.SHAKE, InstabugInvocationEvent.SCREENSHOT)
            .build()
        initKoin()
    }

    private fun initKoin() {
        val modules = listOf(viewModels)
        startKoin(this, modules)
    }

    companion object {
        lateinit var instance: App
    }
}