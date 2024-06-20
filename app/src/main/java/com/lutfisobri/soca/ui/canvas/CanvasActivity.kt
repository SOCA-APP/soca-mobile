package com.lutfisobri.soca.ui.canvas

import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AlertDialog
import com.lutfisobri.soca.R
import com.lutfisobri.soca.data.preference.auth.AuthPreference
import com.lutfisobri.soca.databinding.ActivityCanvasBinding
import com.lutfisobri.soca.ui.BaseActivity
import com.lutfisobri.soca.ui.result.ResultActivity
import java.io.File
import java.io.FileOutputStream

class CanvasActivity : BaseActivity<ActivityCanvasBinding>() {
    private val viewModel by lazy { CanvasViewModel(AuthPreference(this)) }
    private lateinit var progressDialog: AlertDialog

    override fun init() {
        appBar(getString(R.string.canvas))
        setupProgressDialog()

        with(binding) {
            btnClear.setOnClickListener { signaturePad.clear() }

            predict.setOnClickListener { predict() }
        }

        with(viewModel) {
            predict.observe(this@CanvasActivity) { result ->
                dismissProgressDialog()
                navTo(ResultActivity::class.java, result)
            }
            error.observe(this@CanvasActivity) {
                dismissProgressDialog()
                if (!handleError(it)) showDialog()
            }
            api.observe(this@CanvasActivity) {
                dismissProgressDialog()
                showDialog()
            }
        }
    }

    private fun predict() {
        showProgressDialog()

        val bitmap = binding.signaturePad.signatureBitmap
        val file = saveBitmapToFile(bitmap)
        viewModel.predict(file)
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

    private fun setupProgressDialog() {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.progress_dialog, null)
        builder.setView(dialogView)
        builder.setCancelable(false)
        progressDialog = builder.create()
    }

    private fun showProgressDialog() {
        progressDialog.show()
    }

    private fun dismissProgressDialog() {
        progressDialog.dismiss()
    }

    private fun showDialog() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.prediction_title))
            .setMessage("Gagal memprediksi gambar, silahkan coba lagi.")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}