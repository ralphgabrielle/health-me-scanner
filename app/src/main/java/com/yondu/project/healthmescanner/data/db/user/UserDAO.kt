package com.yondu.project.healthmescanner.data.db.user

import androidx.lifecycle.LiveData
import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable

/**
 * Created by Ralph Gabrielle Orden on March 31 2020
 */

@Dao
interface UserDAO {

    @Query("DELETE FROM user")
    fun deleteUser() : Completable

    @Query("SELECT * FROM user LIMIT 1")
    fun currentUser(): Observable<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUser(user: User): Completable

    @Query("SELECT * FROM user LIMIT 1")
    fun currentActiveUser(): LiveData<User>

    @Update
    fun updateUser(user: User): Completable

}