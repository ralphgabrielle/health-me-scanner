package com.yondu.project.healthmescanner.data.repository

import androidx.lifecycle.LiveData
import com.yondu.project.healthmescanner.data.body.LogBody
import com.yondu.project.healthmescanner.data.body.LoginBody
import com.yondu.project.healthmescanner.data.db.user.User
import com.yondu.project.healthmescanner.data.db.user.UserDAO
import com.yondu.project.healthmescanner.data.response.LoginResponse
import com.yondu.project.healthmescanner.data.source.UserDataSource
import com.yondu.project.healthmescanner.http.Wrapper
import io.reactivex.Completable
import io.reactivex.Observable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Ralph Gabrielle Orden on March 31 2020
 */

class UserRepositoryImpl(
    private var userDataSource: UserDataSource,
    private var userDAO: UserDAO
): UserRepository {

    override fun currentUser(): Observable<User> {
        return userDAO.currentUser()
    }

    override fun saveUser(user: User): Completable {
        return userDAO.saveUser(user)
    }

    override suspend fun login(loginBody: LoginBody): LiveData<out Wrapper<LoginResponse>> {
        return withContext(Dispatchers.IO) {
            return@withContext userDataSource.loginAccount(loginBody)
        }
    }

    override suspend fun logAttendance(logBody: LogBody): LiveData<out Wrapper<Any>> {
        return withContext(Dispatchers.IO) {
            return@withContext userDataSource.logAttendance(logBody)
        }
    }
}