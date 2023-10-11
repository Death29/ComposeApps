package com.example.testindocyber.screen.qr.util

import androidx.camera.core.ImageProxy

interface QRCodeFoundListener {
    fun onQRCodeFound(qrCode: String?)
    fun qrCodeNotFound()
    fun onFinish(imageProxy: ImageProxy){}
}