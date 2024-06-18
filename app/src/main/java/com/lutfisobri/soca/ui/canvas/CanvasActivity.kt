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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupProgressDialog()
    }

    override fun init() {
        appBar(getString(R.string.canvas))

        with(binding) {
            btnClear.setOnClickListener { signaturePad.clear() }

            predict.setOnClickListener { predict() }
        }

        viewModel.predict.observe(this) {
            dismissProgressDialog()
            navTo(ResultActivity::class.java, it)
        }
    }

    private fun predict() {
        showProgressDialog()

        Handler(Looper.getMainLooper()).postDelayed({
            val bitmap = binding.signaturePad.signatureBitmap
            val file = saveBitmapToFile(bitmap)
            viewModel.predict(file)
        }, 2000)
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
}