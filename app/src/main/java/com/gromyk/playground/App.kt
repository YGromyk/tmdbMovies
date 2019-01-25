package com.gromyk.playground

import android.app.Application
import com.google.firebase.FirebaseApp
import com.instabug.library.Instabug
import com.instabug.library.invocation.InstabugInvocationEvent
import timber.log.Timber



class App: Application(){
    override fun onCreate() {
        super.onCreate()
        instance = this
        FirebaseApp.initializeApp(this)
        Timber.plant(Timber.DebugTree())
        Instabug.Builder(this, BuildConfig.INSTA_BUG)
            .setInvocationEvents(InstabugInvocationEvent.SHAKE, InstabugInvocationEvent.SCREENSHOT)
            .build()
    }

    companion object {
        lateinit var instance: App
    }
}