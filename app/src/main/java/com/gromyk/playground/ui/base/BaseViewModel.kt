package com.gromyk.playground.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gromyk.playground.utils.networkstate.NetworkState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import org.koin.standalone.KoinComponent
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel(), KoinComponent {
    private val parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.IO
    protected val scope = CoroutineScope(coroutineContext)
    val networkState = MutableLiveData<NetworkState>()
    val titleData = MutableLiveData<String>()

    private fun cancelAllRequests() = coroutineContext.cancel()

    override fun onCleared() {
        cancelAllRequests()
        super.onCleared()
    }

    abstract fun updateTitle()
}