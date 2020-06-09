package com.yondu.project.healthmescanner.data.body

import com.google.gson.annotations.SerializedName

data class LoginBody(

    @SerializedName("location_code")
    var locationCode: String

)