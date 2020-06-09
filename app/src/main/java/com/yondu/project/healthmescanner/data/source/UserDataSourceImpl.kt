package com.yondu.project.healthmescanner.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yondu.project.healthmescanner.http.Wrapper
import com.yondu.project.healthmescanner.base.BaseDataSource
import com.yondu.project.healthmescanner.data.body.LogBody
import com.yondu.project.healthmescanner.data.body.LoginBody
import com.yondu.project.healthmescanner.data.response.LoginResponse
import com.yondu.project.healthmescanner.http.AppNetworkService

/**
 * Created by Ralph Gabrielle Orden on March 31 2020
 */

class UserDataSourceImpl(
    private var networkService: AppNetworkService
): BaseDataSource(), UserDataSource {

    override suspend fun loginAccount(loginBody: LoginBody): LiveData<out Wrapper<LoginResponse>> {
        val tokenLiveData = MutableLiveData<Wrapper<LoginResponse>>()
        val account = networkService.login(loginBody)
        tokenLiveData.postValue(account)
        return tokenLiveData
    }

    override suspend fun logAttendance(logBody: LogBody): LiveData<out Wrapper<Any>> {
        val tokenLiveData = MutableLiveData<Wrapper<Any>>()
        val account = networkService.logAttendance(logBody)
        tokenLiveData.postValue(account)
        return tokenLiveData
    }
}