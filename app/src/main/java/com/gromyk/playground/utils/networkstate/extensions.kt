package com.gromyk.playground.utils.networkstate

import androidx.lifecycle.MutableLiveData


fun MutableLiveData<NetworkState>.onSuccess() = this.postValue(NetworkState.onSuccess())
fun MutableLiveData<NetworkState>.onLoading() = this.postValue(NetworkState.onLoading())
fun MutableLiveData<NetworkState>.onError(error: Throwable?) = this.postValue(NetworkState.onError(error))