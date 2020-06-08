package com.yondu.project.healthmescanner.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yondu.project.apphealthme.http.Wrapper
import com.yondu.project.healthmescanner.base.BaseDataSource
import com.yondu.project.healthmescanner.data.body.LoginBody
import com.yondu.project.healthmescanner.data.local.Token
import com.yondu.project.healthmescanner.http.AppNetworkService

/**
 * Created by Ralph Gabrielle Orden on March 31 2020
 */

class UserDataSourceImpl(
    private var networkService: AppNetworkService
): BaseDataSource(), UserDataSource {

    override suspend fun loginAccount(loginBody: LoginBody): LiveData<out Wrapper<Token>> {
        val tokenLiveData = MutableLiveData<Wrapper<Token>>()
        val account = networkService.login(loginBody)
        tokenLiveData.postValue(account)
        return tokenLiveData
    }
}