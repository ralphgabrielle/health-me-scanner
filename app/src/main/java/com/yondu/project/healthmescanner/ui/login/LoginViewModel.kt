package com.yondu.project.healthmescanner.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yondu.project.healthmescanner.http.Wrapper
import com.yondu.project.healthmescanner.data.body.LoginBody
import com.yondu.project.healthmescanner.data.repository.UserRepository
import com.yondu.project.healthmescanner.data.response.LoginResponse

/*
* Created by Ralph Gabrielle Orden on June 08 2020
*/

class LoginViewModel(
    private var userRepository: UserRepository
): ViewModel() {

    val accountStatus = MutableLiveData<String?>()
    val accountValidationError = MutableLiveData<String?>()

    fun validateAccount(locationCode: String) {
        if (locationCode.isEmpty()) {
            accountValidationError.postValue("Please enter location code.")
            return
        }

        accountStatus.postValue(locationCode)
    }

    suspend fun login(locationCode: String): LiveData<out Wrapper<LoginResponse>> {
        val connectBody = LoginBody(locationCode)
        return userRepository.login(connectBody)
    }

}