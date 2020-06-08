package com.yondu.project.healthmescanner.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yondu.project.healthmescanner.data.db.user.User
import com.yondu.project.healthmescanner.data.db.user.UserDAO

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun userDao(): UserDAO

}