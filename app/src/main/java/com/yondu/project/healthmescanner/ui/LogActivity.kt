package com.yondu.project.healthmescanner.ui

import com.yondu.project.healthmescanner.R
import com.yondu.project.healthmescanner.base.BaseActivity
import kotlinx.android.synthetic.main.activity_log.*

/*
* Created by Ralph Gabrielle Orden on June 08 2020
*/

class LogActivity: BaseActivity() {

    override val layoutId: Int?
        get() =  R.layout.activity_log

    override fun viewCreated() {
        listenToUserEvents()
    }

    private fun listenToUserEvents() {
        bTimeIn.setOnClickListener {
            sendLogAttendance(LogType.TIME_IN)
        }

        bTimeOut.setOnClickListener {
            sendLogAttendance(LogType.TIME_OUT)
        }
    }

    private fun sendLogAttendance(logType: LogType) {

    }
}

enum class LogType {
    TIME_IN, TIME_OUT
}