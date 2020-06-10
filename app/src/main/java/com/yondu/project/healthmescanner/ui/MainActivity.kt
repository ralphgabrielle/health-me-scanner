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
    private var hasScanned = false

    override val layoutId: Int?
        get() = R.layout.activity_main

    override val hasBackButton: Boolean
        get() = true

    override val headerTitle: Int?
        get() = R.string.header_qr

    override fun viewCreated() {
        qrCode.setAutofocusInterval(2000L)
        qrCode.setOnQRCodeReadListener(this)

//        CoroutineScope(Dispatchers.IO).launch {
//
//            // delay 3 seconds
//            delay(3000)
//
//            withContext(Dispatchers.Main) {
//                startActivity<LogActivity>(
//                    "QR" to "5ed3a25a0f7a6f702b29bf33"
//                )
//            }
//        }
    }

    override fun onResume() {
        super.onResume()
        hasScanned = false
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

        if (hasScanned) return
        hasScanned = true

        startActivity<LogActivity>(
            "QR" to text
        )
    }
}
