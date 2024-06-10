package com.lutfisobri.soca.ui.register

import com.lutfisobri.soca.R
import com.lutfisobri.soca.databinding.ActivityRegisterBinding
import com.lutfisobri.soca.ui.BaseActivity
import com.lutfisobri.soca.ui.login.LoginActivity
import com.lutfisobri.soca.utils.gone
import com.lutfisobri.soca.utils.listener.TextListener
import com.lutfisobri.soca.utils.visible

class RegisterActivity : BaseActivity<ActivityRegisterBinding>() {
    private val viewModel by lazy { RegisterViewModel() }

    override fun init() {
        with(binding) {
            tvBackToLogin.setOnClickListener { finish() }
            btnRegister.setOnClickListener { doRegister() }

            edName.addTextChangedListener(object: TextListener {
                override fun onTextListener(s: CharSequence?) {
                    if (s.isNullOrEmpty()) {
                        tvNameError.visible()
                        tvNameError.text = getString(R.string.error_name)
                    } else {
                        tvNameError.gone()
                    }
                }
            })

            edEmail.addTextChangedListener(object: TextListener {
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

            edPassword.addTextChangedListener(object: TextListener {
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

        viewModel.register.observe(this) {
            navTo(LoginActivity::class.java)
        }

        viewModel.error.observe(this) {
        }

        viewModel.apiError.observe(this) {
        }
    }

    private fun doRegister() {
        with(binding) {
            val name = edName.text.toString()
            val email = edEmail.text.toString()
            val password = edPassword.text.toString()
            var isError = false

            if (name.isEmpty()) {
                tvNameError.visible()
                tvNameError.text = getString(R.string.error_name)
                isError = true
            }

            if (email.isEmpty()) {
                tvEmailError.visible()
                tvEmailError.text = getString(R.string.error_email)
                isError = true
            }

            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                tvEmailError.visible()
                tvEmailError.text = getString(R.string.error_email_format)
                isError = true
            }

            if (password.isEmpty()) {
                tvPasswordError.visible()
                tvPasswordError.text = getString(R.string.error_password)
                isError = true
            }

            if (password.length < 6) {
                tvPasswordError.visible()
                tvPasswordError.text = getString(R.string.error_password_length)
                isError = true
            }

            if (!isError) {
                viewModel.register(name, email, password)
            }
        }
    }
}