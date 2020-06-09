package com.yondu.project.healthmescanner.base

import android.app.Activity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.header_with_back.*

abstract class BaseActivity : AppCompatActivity() {

    @get:LayoutRes
    protected abstract val layoutId: Int?

    protected open val hasBackButton = false

    @get:StringRes
    protected open val headerTitle: Int? = null

    protected abstract fun viewCreated()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        layoutId?.let {
            setContentView(it)
            initHeader()
            viewCreated()
        }
    }

    private fun initHeader() {
        headerTitle?.let {
            tvHeaderTitle.text = getString(it)
        }

        containerBack?.let {
            if (!hasBackButton) {
                containerBack.visibility = View.GONE
            } else {
                containerBack.setOnClickListener {
                    finish()
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    open fun hideSoftKeyboard() {
        val activity: Activity = this
        val inputMethodManager =
            activity.getSystemService(
                Activity.INPUT_METHOD_SERVICE
            ) as InputMethodManager
        val currentFocus = activity.currentFocus
        if (currentFocus != null) {
            inputMethodManager.hideSoftInputFromWindow(currentFocus.windowToken, 0)
        }
    }
}