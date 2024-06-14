package com.lutfisobri.soca.ui.canvas

import android.graphics.Bitmap
import android.net.Uri
import com.lutfisobri.soca.R
import com.lutfisobri.soca.data.preference.auth.AuthPreference
import com.lutfisobri.soca.databinding.ActivityCanvasBinding
import com.lutfisobri.soca.ui.BaseActivity
import com.lutfisobri.soca.ui.result.ResultActivity
import com.lutfisobri.soca.utils.gone
import java.io.File
import java.io.FileOutputStream

class CanvasActivity : BaseActivity<ActivityCanvasBinding>() {
    private val viewModel by lazy { CanvasViewModel(AuthPreference(this)) }

    override fun init() {
        appBar(getString(R.string.canvas))

        with(binding) {
            btnClear.setOnClickListener { signaturePad.clear() }

            predict.setOnClickListener { predict() }
        }

        viewModel.predict.observe(this) {
            navTo(ResultActivity::class.java, it)
        }
    }

    private fun predict() {
        with(binding) {
            val bitmap = signaturePad.signatureBitmap
            val file = saveBitmapToFile(bitmap)
            viewModel.predict(file)
        }
    }

    private fun saveBitmapToFile(bitmap: Bitmap): Uri {
        val fileName = System.currentTimeMillis().toString() + "-canvas.jpg"
        val file = File(cacheDir, fileName)
        val outputStream = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, outputStream)
        outputStream.flush()
        outputStream.close()
        return Uri.fromFile(file)
    }
}