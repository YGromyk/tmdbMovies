package com.gromyk.playground.repositories

import com.gromyk.persistence.AppRepository
import com.gromyk.playground.App

/**
 * Created by Yuriy Gromyk on 1/19/19.
 */
object AllDataRepository {
    private lateinit var appRepository: AppRepository

    fun getInstance(): AppRepository {
        if (!::appRepository.isInitialized)
            appRepository = AppRepository(App.instance)
        return appRepository
    }
}