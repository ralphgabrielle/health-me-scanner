package com.yondu.project.healthmescanner.data.repository

import com.yondu.project.healthmescanner.data.db.user.User
import com.yondu.project.healthmescanner.data.db.user.UserDAO
import com.yondu.project.healthmescanner.data.source.UserDataSource
import io.reactivex.Completable
import io.reactivex.Observable

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
}