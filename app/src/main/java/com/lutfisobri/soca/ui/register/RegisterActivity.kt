package com.lutfisobri.soca.ui.register

import android.app.AlertDialog
import android.os.Bundle
import com.lutfisobri.soca.R
import com.lutfisobri.soca.databinding.ActivityRegisterBinding
import com.lutfisobri.soca.ui.BaseActivity
import com.lutfisobri.soca.ui.login.LoginActivity
import com.lutfisobri.soca.utils.gone
import com.lutfisobri.soca.utils.listener.TextListener
import com.lutfisobri.soca.utils.visible

class RegisterActivity : BaseActivity<ActivityRegisterBinding>() {
    private val viewModel by lazy { RegisterViewModel() }
    private lateinit var progressDialog: AlertDialog

    override fun init() {
        setupProgressDialog()
        with(binding) {
            tvBackToLogin.setOnClickListener { finish() }
            btnRegister.setOnClickListener { doRegister() }

            edName.addTextChangedListener(object : TextListener {
                override fun onTextListener(s: CharSequence?) {
                    if (s.isNullOrEmpty()) {
                        tvNameError.visible()
                        tvNameError.text = getString(R.string.error_name)
                    } else {
                        tvNameError.gone()
                    }
                }
            })

            edEmail.addTextChangedListener(object : TextListener {
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

            edPassword.addTextChangedListener(object : TextListener {
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
            dismissProgressDialog()
            showSuccessDialog()
        }

        viewModel.error.observe(this) {
            dismissProgressDialog()
            showErrorDialog()
        }

        viewModel.apiError.observe(this) {
            dismissProgressDialog()
            showErrorDialog()
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
                showProgressDialog()
                viewModel.register(name, email, password)
            }
        }
    }

    private fun setupProgressDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setView(R.layout.progress_dialog)
        builder.setCancelable(false)
        progressDialog = builder.create()
    }

    private fun showProgressDialog() {
        progressDialog.show()
    }

    private fun dismissProgressDialog() {
        if (progressDialog.isShowing) {
            progressDialog.dismiss()
        }
    }

    private fun showSuccessDialog() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.register_success_title))
            .setMessage(getString(R.string.register_success_message))
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
                navToFinish(LoginActivity::class.java)
            }
            .show()
    }

    private fun showErrorDialog() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.register_error_title))
            .setMessage(getString(R.string.register_error_message))
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}