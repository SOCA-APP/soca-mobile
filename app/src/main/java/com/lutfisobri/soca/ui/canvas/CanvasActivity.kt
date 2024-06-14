package com.lutfisobri.soca.ui.canvas

import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AlertDialog
import com.lutfisobri.soca.R
import com.lutfisobri.soca.databinding.ActivityCanvasBinding
import com.lutfisobri.soca.ui.BaseActivity

class CanvasActivity : BaseActivity<ActivityCanvasBinding>() {

    override fun init() {
        appBar(getString(R.string.canvas))

        with(binding) {
            btnClear.setOnClickListener { signaturePad.clear() }

            predict.setOnClickListener { predict() }
        }
    }

    private fun predict() {
        val progressDialog = AlertDialog.Builder(this)
            .setMessage(getString(R.string.loading))
            .setCancelable(false)
            .create()

        progressDialog.show()

        Handler(Looper.getMainLooper()).postDelayed({
            val isPredicted = true // Gantikan dengan logika prediksi yang sesungguhnya

            if (isPredicted) {
                showPredictionDialog(getString(R.string.prediction_result))
            } else {
                showPredictionDialog(getString(R.string.prediction_failed))
            }

            progressDialog.dismiss()
        }, 2000)
    }

    private fun showPredictionDialog(message: String) {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.prediction_title))
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}