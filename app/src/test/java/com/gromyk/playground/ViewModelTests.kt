/**
 * Created by Yurii Gromyk
 * @date 3/2/19 7:25 PM
 * @author gromyk
 *
 * Copyright (c) 2019.
 **/

package com.gromyk.playground

import com.gromyk.playground.di.viewModels
import com.gromyk.playground.ui.MainViewModel
import com.gromyk.playground.ui.movies.TmdbViewModel
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.standalone.StandAloneContext.stopKoin
import org.koin.standalone.inject
import org.koin.test.KoinTest

class ViewModelTests : KoinTest {
    @Before
    fun before() {
        startKoin(listOf(viewModels))
    }

    @Test
    fun testTmdbViewModel() {
        val viewModel: TmdbViewModel by inject()
        assert(viewModel::class.java == TmdbViewModel::class.java)
    }

    @Test
    fun testMainViewModel() {
        val viewModel: MainViewModel by inject()
        viewModel.updateTitle()
        assert(viewModel.titleData.value == null)
    }

    @After
    fun after() {
        stopKoin()
    }
}