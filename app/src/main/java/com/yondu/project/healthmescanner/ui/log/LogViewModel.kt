package com.yondu.project.healthmescanner.ui.log

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.yondu.project.healthmescanner.http.Wrapper
import com.yondu.project.healthmescanner.data.body.LogBody
import com.yondu.project.healthmescanner.data.repository.UserRepository

/*
* Created by Ralph Gabrielle Orden on June 08 2020
*/

class LogViewModel(private var userRepository: UserRepository
): ViewModel() {

    suspend fun logAttendance(logType: LogType, locationId: String): LiveData<out Wrapper<Any>> {
        val logBody = LogBody(logType.name, locationId)
        return userRepository.logAttendance(logBody)
    }

}