package com.yondu.project.healthmescanner.util

import android.os.Build

/**
 * Created by Ralph Gabrielle Orden on 9/25/2019.
 */

object DeviceUtil {

    fun isMarshmallowOrAbove() : Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
    }

}