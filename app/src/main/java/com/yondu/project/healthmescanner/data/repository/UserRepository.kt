package com.yondu.project.healthmescanner.data.repository

import androidx.lifecycle.LiveData
import com.yondu.project.apphealthme.http.Wrapper
import com.yondu.project.healthmescanner.data.body.LoginBody
import com.yondu.project.healthmescanner.data.db.user.User
import com.yondu.project.healthmescanner.data.response.LoginResponse
import io.reactivex.Completable
import io.reactivex.Observable

/**
 * Created by Ralph Gabrielle Orden on March 31 2020
 */

interface UserRepository {

    fun currentUser(): Observable<User>

    fun saveUser(user: User): Completable

    suspend fun login(loginBody: LoginBody): LiveData<out Wrapper<LoginResponse>>
}