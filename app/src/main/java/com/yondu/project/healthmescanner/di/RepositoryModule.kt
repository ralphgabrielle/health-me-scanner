package com.yondu.project.healthmescanner.di

import com.yondu.project.healthmescanner.data.repository.UserRepositoryImpl
import com.yondu.project.healthmescanner.data.repository.UserRepository
import com.yondu.project.healthmescanner.data.source.UserDataSource
import com.yondu.project.healthmescanner.data.source.UserDataSourceImpl
import org.koin.dsl.module.module

val repositoryModule = module {

    factory<UserRepository> { UserRepositoryImpl(get(), get()) }

    factory<UserDataSource> { UserDataSourceImpl(get()) }

}