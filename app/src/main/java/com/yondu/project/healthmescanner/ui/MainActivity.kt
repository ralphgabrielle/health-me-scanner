package com.yondu.project.healthmescanner.ui

import android.graphics.PointF
import com.dlazaro66.qrcodereaderview.QRCodeReaderView
import com.yondu.project.healthmescanner.R
import com.yondu.project.healthmescanner.base.BaseActivity
import com.yondu.project.healthmescanner.ui.log.LogActivity
import com.yondu.project.healthmescanner.util.PreferenceManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity(), QRCodeReaderView.OnQRCodeReadListener {

    private val preferenceManager: PreferenceManager by inject()

    override val layoutId: Int?
        get() = R.layout.activity_main

    override val hasBackButton: Boolean
        get() = true

    override fun viewCreated() {
        qrCode.setOnQRCodeReadListener(this)
    }

    override fun onResume() {
        super.onResume()
        qrCode.startCamera()
    }

    override fun onPause() {
        super.onPause()
        qrCode.stopCamera()
    }

    override fun finish() {
        preferenceManager.clearAll()
        super.finish()
    }

    override fun onQRCodeRead(text: String?, points: Array<out PointF>?) {
        qrCode.stopCamera()

        startActivity<LogActivity>(
            "QR" to text
        )
    }
}
