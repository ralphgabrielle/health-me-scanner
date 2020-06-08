package com.yondu.project.healthmescanner.di

import com.yondu.project.healthmescanner.data.repository.UserRepositoryImpl
import com.yondu.project.healthmescanner.data.repository.UserRepository
import org.koin.dsl.module.module

val repositoryModule = module {

    factory<UserRepository> { UserRepositoryImpl(get(), get()) }



}