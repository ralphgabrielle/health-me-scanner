package com.yondu.project.healthmescanner.base

import java.util.HashMap

/*
* Created by Ralph Gabrielle Orden on May 13 2020
*/

open class BaseDataSource {

    protected fun headers(token: String): HashMap<String, String> {
        val headers = HashMap<String, String>()

        headers["Content-Type"] = "application/json"
        headers["Accept"] = "application/json"
        headers["Authorization"] = String.format("Bearer %s", token)

        return headers
    }

}