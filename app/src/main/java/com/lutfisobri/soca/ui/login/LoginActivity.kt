package com.lutfisobri.soca.ui.login

import com.lutfisobri.soca.R
import com.lutfisobri.soca.data.preference.auth.AuthPreference
import com.lutfisobri.soca.databinding.ActivityLoginBinding
import com.lutfisobri.soca.ui.BaseActivity
import com.lutfisobri.soca.ui.dashboard.DashboardActivity
import com.lutfisobri.soca.ui.register.RegisterActivity
import com.lutfisobri.soca.utils.gone
import com.lutfisobri.soca.utils.listener.TextListener
import com.lutfisobri.soca.utils.visible

class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    private val viewModel by lazy { LoginViewModel(AuthPreference(this)) }

    override fun init() {
        with(binding) {
            tvCreateAccount.setOnClickListener { navTo(RegisterActivity::class.java) }
            btnLogin.setOnClickListener { doLogin() }

            edEmail.addTextChangedListener(object: TextListener{
                override fun onTextListener(s: CharSequence?) {
                    if (s.isNullOrEmpty()) {
                        tvEmailError.visible()
                        tvEmailError.text = getString(R.string.error_email)
                    }

                    s?.let {
                        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(it).matches()) {
                            tvEmailError.visible()
                            tvEmailError.text = getString(R.string.error_email_format)
                        } else {
                            tvEmailError.gone()
                        }
                    }
                }
            })

            edPassword.addTextChangedListener(object: TextListener{
                override fun onTextListener(s: CharSequence?) {
                    if (s.isNullOrEmpty()) {
                        tvPasswordError.visible()
                        tvPasswordError.text = getString(R.string.error_password)
                    }

                    s?.let {
                        if (it.length < 6) {
                            tvPasswordError.visible()
                            tvPasswordError.text = getString(R.string.error_password_length)
                        } else {
                            tvPasswordError.gone()
                        }
                    }
                }
            })
        }
    }

    private fun doLogin() {
        with(binding) {
            val email = edEmail.text.toString()
            val password = edPassword.text.toString()
            var isError = false

            if (email.isEmpty()) {
                tvEmailError.visible()
                tvEmailError.text = getString(R.string.error_email)
                isError = true
            }

            if (password.isEmpty()) {
                tvPasswordError.visible()
                tvPasswordError.text = getString(R.string.error_password)
                isError = true
            }

            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                tvEmailError.visible()
                tvEmailError.text = getString(R.string.error_email_format)
                isError = true
            }

            if (password.length < 6) {
                tvPasswordError.visible()
                tvPasswordError.text = getString(R.string.error_password_length)
                isError = true
            }

            if (isError) return
            viewModel.login(email, password)
        }

        with(viewModel) {
            login.observe(this@LoginActivity) {
                navToFinish(DashboardActivity::class.java)
            }
            error.observe(this@LoginActivity) {
                println(it)
            }
            apiError.observe(this@LoginActivity) {
                println(it)
            }
        }
    }
}