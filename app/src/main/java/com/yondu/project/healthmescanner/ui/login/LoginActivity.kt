package com.yondu.project.healthmescanner.ui.login

import android.Manifest
import android.view.View
import androidx.lifecycle.Observer
import com.yondu.project.healthmescanner.R
import com.yondu.project.healthmescanner.base.BaseActivity
import com.yondu.project.healthmescanner.data.response.LoginResponse
import com.yondu.project.healthmescanner.extras.catchError
import com.yondu.project.healthmescanner.extras.confirmOK
import com.yondu.project.healthmescanner.extras.readString
import com.yondu.project.healthmescanner.extras.simpleOK
import com.yondu.project.healthmescanner.ui.MainActivity
import com.yondu.project.healthmescanner.util.Constant.REQUEST_QR_CODE
import com.yondu.project.healthmescanner.util.DeviceUtil
import com.yondu.project.healthmescanner.util.PermissionAccessManager
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.anko.*
import org.koin.android.ext.android.inject

/*
* Created by Ralph Gabrielle Orden on June 08 2020
*/

class LoginActivity : BaseActivity(), PermissionAccessManager.AccessPermission {

    private val viewModel: LoginViewModel by inject()
    private lateinit var permissionManageAccessManager: PermissionAccessManager

    private val cameraPermission = arrayOf(
        Manifest.permission.CAMERA)

    override val layoutId: Int?
        get() = R.layout.activity_login

    override fun viewCreated() {
        permissionManageAccessManager = PermissionAccessManager(
            context = this,
            requestCode = REQUEST_QR_CODE,
            permissions = cameraPermission,
            accessPermission = this
        )

        observeEvents()
        listenUserEvents()
    }

    private fun observeEvents() {
        viewModel.accountValidationError.observe(this, Observer {
            it?: return@Observer

            toast(it)
        })

        viewModel.accountStatus.observe(this, Observer {
            it?: return@Observer

            login(it)
        })
    }

    private fun listenUserEvents() {
        bTest.visibility = View.GONE

        bNext.setOnClickListener {
            connectDevice()
        }
    }

    private fun connectDevice() {
        val locationCode = etLocationCode.readString()

        viewModel.validateAccount(locationCode)
    }

    private fun login(locationId: String) = CoroutineScope(Dispatchers.Main).launch {
        val dialog = indeterminateProgressDialog(
            message = "Connecting, please wait",
            title = "Info"
        )

        try {
            dialog.setCancelable(false)
            dialog.show()

            viewModel.login(locationId).observe(this@LoginActivity, Observer {
                it ?: return@Observer

                saveLocationId(it.objectData)
                proceedToQRScan()
            })
        } catch (ex: Exception) {
            dialog.dismiss()
            catchError(ex) {
                confirmOK("There was an error, please try", "Error") {
                    okButton { }
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        permissionManageAccessManager.onRequestPermissionsResult(
            requestCode, permissions, grantResults
        )
    }

    private fun saveLocationId(loginResponse: LoginResponse?) {
        loginResponse?.let {

        }
    }

    private fun proceedToQRScan() {
        if (DeviceUtil.isMarshmallowOrAbove()) {
            askCameraPermission()
        } else {
            startActivity(
                intentFor<MainActivity>()
                    .clearTask()
                    .newTask()
            )
        }
    }

    private fun askCameraPermission() {
        if (permissionManageAccessManager.isAllPermissionGranted()) {
            startActivity(
                intentFor<MainActivity>()
                    .clearTask()
                    .newTask())
        } else {
            permissionManageAccessManager.checkPermission()
        }
    }

    override fun onAskPermission(permissions: Array<out String>, requestCode: Int) {
        requestPermissions(permissions, requestCode)
    }

    override fun onPermissionGranted(requestCode: Int) {
        when (requestCode) {
            REQUEST_QR_CODE -> proceedToQRScan()
        }
    }

    override fun onShowRequestRationale() {
        alert(R.string.message_permission_granted, R.string.title_error) {
            okButton {
                permissionManageAccessManager.openSettings()
            }
        }.show()
    }

    override fun onPermissionNotGrantedOnSome() {
        simpleOK(R.string.message_permission_granted, R.string.title_error)
    }
}