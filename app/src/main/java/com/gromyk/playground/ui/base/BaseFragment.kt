/**
 * Created by Yurii Gromyk
 * @date 3/2/19 5:37 PM
 * @author gromyk
 *
 * Copyright (c) 2019.
 **/

package com.gromyk.playground.ui.base

import android.os.Bundle
import android.view.View
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.gromyk.playground.utils.networkstate.NetworkState

abstract class BaseFragment : Fragment() {
    protected abstract val viewModel: BaseViewModel
    protected abstract fun onNetworkStateChanged(networkState: NetworkState)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeTitleData()
    }

    private fun setToolbarTitle(title: String?) {
        if (activity is ToolbarInteractor) {
            (activity as ToolbarInteractor).apply {
                setToolbarTitle(title)
            }
        }
    }

    @Suppress("unused")
    private fun setToolbarIcon(@DrawableRes iconId: Int) {
        if (activity is ToolbarInteractor) {
            (activity as ToolbarInteractor).apply {
                setToolbarIcon(iconId)
            }
        }
    }

    private fun subscribeTitleData() {
        viewModel.titleData.observe(this, Observer { setToolbarTitle(it) })
    }

    override fun onResume() {
        super.onResume()
        viewModel.updateTitle()
    }
}