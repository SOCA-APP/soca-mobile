package com.lutfisobri.soca.ui.login

import com.lutfisobri.soca.databinding.ActivityLoginBinding
import com.lutfisobri.soca.ui.BaseActivity
import com.lutfisobri.soca.ui.dashboard.DashboardActivity
import com.lutfisobri.soca.ui.register.RegisterActivity

class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    override fun init() {
        with(binding) {
            tvCreateAccount.setOnClickListener { navTo(RegisterActivity::class.java) }
            btnLogin.setOnClickListener { doLogin() }
        }
    }

    private fun doLogin() {
        navToFinish(DashboardActivity::class.java)
    }
}