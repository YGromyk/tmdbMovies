package com.gromyk.playground

import android.app.Application
import com.instabug.library.Instabug
import com.instabug.library.invocation.InstabugInvocationEvent

class App: Application(){
    override fun onCreate() {
        super.onCreate()

        Instabug.Builder(this, BuildConfig.INSTA_BUG)
            .setInvocationEvents(InstabugInvocationEvent.SHAKE, InstabugInvocationEvent.SCREENSHOT)
            .build()
    }
}