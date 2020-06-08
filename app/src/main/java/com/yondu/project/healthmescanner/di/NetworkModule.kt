package com.yondu.project.healthmescanner.di

import com.yondu.project.healthmescanner.http.AppNetworkService
import org.koin.dsl.module.module

/**
 * Created by Ralph Gabrielle Orden on March 18 2020
 */

val networkModule = module{

    single {  AppNetworkService() }

}