package com.yondu.project.healthmescanner.data.source

import androidx.lifecycle.LiveData
import com.yondu.project.apphealthme.http.Wrapper
import com.yondu.project.healthmescanner.data.body.LoginBody
import com.yondu.project.healthmescanner.data.local.Token

/**
 * Created by Ralph Gabrielle Orden on March 31 2020
 */

interface UserDataSource {

    suspend fun loginAccount(loginBody: LoginBody): LiveData<out Wrapper<Token>>


}