package com.yondu.project.healthmescanner.extras

import android.content.Context
import android.content.DialogInterface
import android.widget.*
import org.jetbrains.anko.AlertBuilder
import org.jetbrains.anko.alert
import org.jetbrains.anko.okButton
import timber.log.Timber
import java.net.ConnectException
import java.net.UnknownHostException

fun TextView.readString() = this.text.toString()

fun emptyString() = ""

fun plantLog(message: String?) = Timber.d(message ?: "Null message received")

fun RadioGroup.readString(): String {
    val selectedId: Int = checkedRadioButtonId
    return findViewById<RadioButton>(selectedId).text.toString()
}

fun Context.simpleOK(messageResource: Int, titleResource: Int) {
    alert(messageResource, titleResource) {
        okButton {}
    }.show()
}

fun Context.simpleOK(messageResource: String, titleResource: String) {
    alert(messageResource, titleResource) {
        okButton {}
    }.show()
}


fun Context.confirmOK(
    message: String,
    title: String,
    init: (AlertBuilder<DialogInterface>.() -> Unit)?
) {
    alert(message, title) {
        if (init != null) init()
    }.show()
        .setCancelable(false)
}

fun Context.catchError(ex: java.lang.Exception, elseException: () -> Unit?) {
    when (ex) {
        is ConnectException -> {
            confirmOK("No internet connection, please try again", "Error") {
                okButton { }
            }
        }
        is UnknownHostException -> {
            confirmOK("No internet connection, please try again", "Error") {
                okButton { }
            }
        }
        else -> {
            elseException()
        }
    }
}