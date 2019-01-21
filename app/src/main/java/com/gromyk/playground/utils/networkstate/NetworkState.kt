package com.gromyk.playground.utils.networkstate

import androidx.annotation.IntDef

class NetworkState private constructor() {
    @Status
    var status: Int = SUCCESS
    var error: Throwable? = null

    companion object {
        fun onSuccess() = NetworkState().apply {
            status = SUCCESS
        }

        fun onLoading() = NetworkState().apply {
            status = LOADING
        }

        fun onError(error: Throwable?) = NetworkState().apply {
            status = FAILED
            this.error = error
        }

        @IntDef(
            SUCCESS,
            LOADING,
            FAILED
        )
        @Retention(AnnotationRetention.SOURCE)
        annotation class Status

        @Status
        const val SUCCESS = 0
        @Status
        const val LOADING = 1
        @Status
        const val FAILED = 2
    }
}