package com.makeup.makeupapptest.koin

import com.makeup.makeupapptest.home.viewmodels.MainActivityViewModel
import com.makeup.makeupapptest.home.viewmodels.MakeUpListFragmentViewModel
import com.makeup.makeupapptest.home.viewmodels.SortingDataDialogViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainActivityViewModel() }
    viewModel { MakeUpListFragmentViewModel() }
    viewModel { SortingDataDialogViewModel() }
}