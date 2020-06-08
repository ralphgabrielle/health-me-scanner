package com.yondu.project.healthmescanner.data.db.user

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/*
* Created by Ralph Gabrielle Orden on June 08 2020
*/

@Entity(tableName = "user")
data class User(

    @PrimaryKey
    @SerializedName(value = "_id")
    var id: String

)