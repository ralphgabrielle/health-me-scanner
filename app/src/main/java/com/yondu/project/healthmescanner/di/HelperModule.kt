package com.yondu.project.healthmescanner.di

import com.yondu.project.healthmescanner.util.PreferenceManager
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module

/**
 * Created by Ralph Gabrielle Orden on March 18 2020
 */

val managerModule = module {

    single { PreferenceManager(androidApplication()) }

}