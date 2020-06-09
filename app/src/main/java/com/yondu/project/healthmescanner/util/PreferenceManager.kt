package com.yondu.project.healthmescanner.util

import android.content.Context
import android.content.SharedPreferences
import com.yondu.project.healthmescanner.extras.emptyString


/**
 * Created by Ralph Gabrielle Orden on 9/6/2019.
 */
class PreferenceManager constructor(context: Context) {

    companion object {
        const val SHARED_PREFERENCE_NAME = "com.yondu.project.healthmescanner.SHARED_PREFERENCE_NAME"

        const val LOCATION_ID = "$SHARED_PREFERENCE_NAME.LOCATION_ID"

        private const val DEFAULT_VALUE = ""
    }

    private var pref: SharedPreferences

    init {
        pref = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
    }

    fun clearAll() {
        pref.edit().apply {
            clear()
            apply()
        }
    }

    fun saveLocationId(locationID: String) {
        pref.edit().apply{
            putString(LOCATION_ID, locationID)
            apply()
        }
    }

    fun getLocationId(): String? {
        return pref.getString(LOCATION_ID, emptyString())
    }


}