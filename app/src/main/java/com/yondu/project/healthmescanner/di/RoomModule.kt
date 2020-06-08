package com.yondu.project.healthmescanner.di

import androidx.room.Room
import com.yondu.project.healthmescanner.data.db.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module


/**
 * Created by Ralph Gabrielle Orden on March 18 2020
 */

val roomModule = module {

    single { Room.databaseBuilder(androidApplication(), AppDatabase::class.java, "yondu_healthmescanner.db").build() }

    single { get<AppDatabase>().userDao() }

}