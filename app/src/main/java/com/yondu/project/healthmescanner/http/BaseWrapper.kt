package com.yondu.project.apphealthme.http

import com.yondu.project.apphealthme.extras.emptyString


/**
 * Created by Ralph Gabrielle Orden on 3/18/2020.
 */
open class BaseWrapper<T> {

    var status: Int = 0
    var message: String = emptyString()

}