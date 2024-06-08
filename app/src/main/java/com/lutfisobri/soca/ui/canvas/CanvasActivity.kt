package com.lutfisobri.soca.ui.canvas

import com.lutfisobri.soca.R
import com.lutfisobri.soca.databinding.ActivityCanvasBinding
import com.lutfisobri.soca.ui.BaseActivity

class CanvasActivity : BaseActivity<ActivityCanvasBinding>() {
    override fun init() {
        appBar(getString(R.string.canvas))

        with(binding) {
            btnClear.setOnClickListener { signaturePad.clear() }

            predict.setOnClickListener{ predict() }
        }
    }

    private fun predict() {}
}