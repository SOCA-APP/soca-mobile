package com.lutfisobri.soca.ui.login

import com.lutfisobri.soca.databinding.ActivityLoginBinding
import com.lutfisobri.soca.ui.BaseActivity
import com.lutfisobri.soca.ui.register.RegisterActivity

class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    override fun init() {
        with(binding) {
            tvCreateAccount.setOnClickListener { navTo(RegisterActivity::class.java) }
        }
    }

    private fun doLogin() {}
}