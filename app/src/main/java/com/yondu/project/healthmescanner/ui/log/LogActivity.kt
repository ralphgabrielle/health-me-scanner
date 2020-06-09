package com.yondu.project.healthmescanner.ui.log

import androidx.lifecycle.Observer
import com.yondu.project.healthmescanner.R
import com.yondu.project.healthmescanner.base.BaseActivity
import com.yondu.project.healthmescanner.extras.catchError
import com.yondu.project.healthmescanner.extras.confirmOK
import com.yondu.project.healthmescanner.util.PreferenceManager
import kotlinx.android.synthetic.main.activity_log.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.okButton
import org.koin.android.ext.android.inject
import java.lang.Exception

/*
 * Created by Ralph Gabrielle Orden on June 08 2020
 */

class LogActivity: BaseActivity() {

    private val preferenceManager: PreferenceManager by inject()
    private val viewModel: LogViewModel by inject()

    override val hasBackButton: Boolean
        get() = true

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

    private fun sendLogAttendance(logType: LogType) = CoroutineScope(Dispatchers.Main).launch {
        val dialog = indeterminateProgressDialog(
            message = "Connecting, please wait",
            title = "Info"
        )

        try {
            dialog.setCancelable(false)
            dialog.show()

            val qrCode = intent.getStringExtra("QR")!!

            preferenceManager.getLocationId()?.let { location ->
                viewModel.logAttendance(qrCode, logType, location).observe(this@LogActivity, Observer {
                    it?: return@Observer

                    dialog.dismiss()
                    success()
                })
            }
        } catch (ex: Exception) {
            dialog.dismiss()
            catchError(ex) {
                finish()
            }
        }
    }

    private fun success() {
//        confirmOK("Your attendance log has been sent successfully", "Success") {
//            okButton {
//                finish()
//            }
//        }

        finish()
    }
}

enum class LogType(val mode: String) {
    TIME_IN("INBOUND"), TIME_OUT("OUTBOUND")
}