package com.example.testindocyber.screen.qr.util

import android.annotation.SuppressLint
import android.graphics.ImageFormat
import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.common.InputImage

class QRCodeImageAnalyzer(private val listener: QRCodeFoundListener, private val scanner: BarcodeScanner, private val qrResult: (result: String) -> Unit) : ImageAnalysis.Analyzer {
    private val yuvFormats = mutableListOf(ImageFormat.YUV_420_888)

    init {
        yuvFormats.addAll(listOf(ImageFormat.YUV_422_888, ImageFormat.YUV_444_888))
    }

    @SuppressLint("UnsafeOptInUsageError")
    override fun analyze(imageProx: ImageProxy) {
        val mediaImage = imageProx.image
        if (mediaImage != null) {

            // Pass image to the scanner and have it do its thing
            if (imageProx.format !in yuvFormats) {
                Log.e("QRCodeAnalyzer", "Expected YUV, now = ${imageProx.format}")
                return
            } else {
                val image = InputImage.fromMediaImage(mediaImage, imageProx.imageInfo.rotationDegrees)
                scanner.process(image)
                    .addOnSuccessListener { barcodes ->
                        // Task completed successfully
                        for (barcode in barcodes) {
                            //listener.onQRCodeFound(barcode.rawValue)
                            qrResult(barcode.rawValue.toString())
                        }
                        //scanner.close()
                    }
                    .addOnFailureListener {
                        listener.qrCodeNotFound()
                    }
                    .addOnCompleteListener {
                        // It's important to close the imageProxy
                        imageProx.close()
                        listener.onFinish(imageProx)
                    }
            }

        }
    }
}