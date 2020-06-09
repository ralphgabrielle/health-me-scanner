package com.yondu.project.healthmescanner.http

import com.yondu.project.healthmescanner.extras.emptyString


/**
 * Created by Ralph Gabrielle Orden on 3/18/2020.
 */
open class BaseWrapper<T> {

    var status: Int = 0
    var message: String = emptyString()

}