package com.yondu.project.healthmescanner.data.body

import com.google.gson.annotations.SerializedName

/*
* Created by Ralph Gabrielle Orden on June 08 2020
*/

data class LogBody (

    @SerializedName("profile_id")
    var qrCode: String,

    @SerializedName("mode")
    var logtype: String,

    @SerializedName("location_code")
    var locationId: String

)