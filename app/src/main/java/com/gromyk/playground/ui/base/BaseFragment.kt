package com.gromyk.playground.ui.base

import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import com.gromyk.playground.utils.networkstate.NetworkState

abstract class BaseFragment: Fragment() {
    protected abstract val progressView: ProgressBar?
    protected abstract fun onNetworkStateChanged(networkState: NetworkState)
}