package com.lutfisobri.soca.ui.login

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
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
    private lateinit var progressDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupProgressDialog()
    }

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

            if (isError) {
                showProgressDialog()
                progressDialog.dismiss()
                return
            }
            viewModel.login(email, password)
        }

        with(viewModel) {
            login.observe(this@LoginActivity) {
                dismissProgressDialog()
                showSuccessDialog()
            }
            error.observe(this@LoginActivity) {
                dismissProgressDialog()
                showErrorDialog(getString(R.string.login_error_message_invalid_credentials))
            }
            apiError.observe(this@LoginActivity) {
                dismissProgressDialog()
                showErrorDialog(getString(R.string.login_error_message_generic))
            }
        }
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

    private fun showSuccessDialog() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.login_success_title))
            .setMessage(getString(R.string.login_success_message))
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
                navToFinish(DashboardActivity::class.java)
            }
            .show()
    }

    private fun showErrorDialog(message: String) {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.login_error_title))
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}