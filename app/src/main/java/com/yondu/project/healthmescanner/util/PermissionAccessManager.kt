package com.yondu.project.healthmescanner.util

import android.content.Context
import androidx.core.content.ContextCompat
import android.net.Uri.fromParts
import android.content.Intent
import android.app.Activity
import android.provider.Settings
import android.content.pm.PackageManager
import androidx.annotation.NonNull
import android.os.Build
import androidx.annotation.RequiresApi


/*
 * Created by Ralph Gabrielle Orden on 9/12/2019.
 */

class PermissionAccessManager(private val context: Context,
                              private val requestCode: Int,
                              private val permissions: Array<String>,
                              private val accessPermission: AccessPermission) {

    fun checkPermission() {
        val notEmpty = permissions.isNotEmpty()
        if (notEmpty) {
            val permissions = returnNoAccessPermission()
            if (permissions.isEmpty()) {
                accessPermission.onPermissionGranted(requestCode)
            } else {
                accessPermission.onAskPermission(permissions, requestCode)
            }
        }
    }

    private fun returnNoAccessPermission(): Array<String> {
        val noAccessPermissionList = ArrayList<String>()

        for (permission in permissions) {
            ContextCompat.checkSelfPermission(context, permission).let {
                if (it != 0) {
                    noAccessPermissionList.add(permission)
                }
            }
        }

        if (noAccessPermissionList.isNotEmpty()) {
            val size = noAccessPermissionList.size
            return noAccessPermissionList.toArray(arrayOfNulls<String>(size))
        }

        return emptyArray()
    }

    fun isAllPermissionGranted(): Boolean {
        return returnNoAccessPermission().isEmpty()
    }

    fun openSettings() {
        val activity = context as Activity
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = fromParts("package", context.getPackageName(), null)
        intent.data = uri

        activity.startActivityForResult(intent, Constant.REQUEST_CHECK_SETTINGS)
    }

    private fun isAllIsGranted(grantResults: IntArray): Boolean {
        val granted = PackageManager.PERMISSION_GRANTED
        for (category in grantResults) {
            if (category != granted) {
                return false
            }
        }

        return true
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == this.requestCode) {
            var isRationale = false

            if (grantResults.isNotEmpty()) {
                if (isAllIsGranted(grantResults)) {
                    /*
                     * re call this method.
                     *
                     */
                    accessPermission.onPermissionGranted(requestCode)
                } else {
                    /*
                     * Not all permissions are granted.
                     */
                    for (permission in permissions) {
                        val shouldShowRequestPermissionRationale =
                            (context as Activity).shouldShowRequestPermissionRationale(permission)

                        val isGranted =
                            ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED

                        // false true = deny, not never ask again
                        // false false = deny, never ask again

                        if (!shouldShowRequestPermissionRationale && !isGranted) {
                            /*
                             * For Never Ask Again tick option
                             * Force user to turn on permission
                             */
                            accessPermission.onShowRequestRationale()
                            isRationale = true
                            break
                        }
                    }

                    if (!isRationale) {
                        accessPermission.onPermissionNotGrantedOnSome()
                    }
                }
            }
        }
    }

    interface AccessPermission {
        fun onAskPermission(@NonNull permissions: Array<out String>, requestCode: Int)
        fun onPermissionGranted(requestCode: Int)
        fun onShowRequestRationale()
        fun onPermissionNotGrantedOnSome()
    }
}