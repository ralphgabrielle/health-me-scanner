package com.yondu.project.healthmescanner.di

import com.yondu.project.healthmescanner.ui.login.LoginViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModelModule = module {

    viewModel { LoginViewModel(get()) }


}