package com.yondu.project.healthmescanner.http

import com.google.gson.annotations.SerializedName

/**
 * Created by Ralph Gabrielle Orden on 3/18/2020.
 */
class Wrapper<T> : BaseWrapper<T>() {

    @SerializedName("data")
    var objectData : T? = null

}