package com.gromyk.playground

import android.app.Application
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseApp
import com.google.firebase.iid.FirebaseInstanceId
import com.gromyk.playground.di.api
import com.gromyk.playground.di.repositories
import com.gromyk.playground.di.viewModels
import com.gromyk.playground.utils.resources.ResourceProvider
import com.gromyk.playground.utils.resources.ResourceProviderImpl
import com.instabug.library.Instabug
import com.instabug.library.invocation.InstabugInvocationEvent
import org.koin.android.ext.android.startKoin
import org.koin.android.logger.AndroidLogger
import timber.log.Timber


class App : Application() {
    private lateinit var resourceProvider: ResourceProviderImpl
    override fun onCreate() {
        super.onCreate()
        instance = this
        Timber.plant(Timber.DebugTree())
        Instabug.Builder(this, BuildConfig.INSTA_BUG)
            .setInvocationEvents(InstabugInvocationEvent.SHAKE, InstabugInvocationEvent.SCREENSHOT)
            .build()
        initKoin()
        fireBaseInit()
    }

    private fun initKoin() {
        val modules = listOf(viewModels, api, repositories)
        startKoin(
            androidContext = this,
            modules = modules,
            logger = AndroidLogger()
        )
    }

    private fun fireBaseInit() {
        FirebaseApp.initializeApp(this)

        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Timber.w("getInstanceId failed  ${task.exception}")
                    return@OnCompleteListener
                }

                // Get new Instance ID token
                val token = task.result?.token
                Timber.d(token)
            })
    }

    fun getResourceProvider(): ResourceProvider {
        if (!::resourceProvider.isInitialized)
            resourceProvider = ResourceProviderImpl().apply {
                context = this@App
            }
        return resourceProvider
    }

    companion object {
        lateinit var instance: App
    }
}