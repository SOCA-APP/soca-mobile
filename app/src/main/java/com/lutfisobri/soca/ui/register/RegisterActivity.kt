package com.lutfisobri.soca.ui.register

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.lutfisobri.soca.R
import com.lutfisobri.soca.databinding.ActivityRegisterBinding
import com.lutfisobri.soca.ui.BaseActivity
import com.lutfisobri.soca.ui.login.LoginActivity

class RegisterActivity : BaseActivity<ActivityRegisterBinding>() {
    override fun init() {
        with(binding) {
            tvBackToLogin.setOnClickListener { finish() }
        }
    }

    private fun doRegister() {}
}