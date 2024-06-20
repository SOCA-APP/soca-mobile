package com.lutfisobri.soca.ui

import android.app.ActivityOptions
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.graphics.translationMatrix
import androidx.viewbinding.ViewBinding
import com.lutfisobri.soca.R
import com.lutfisobri.soca.data.network.response.BaseResponse
import com.lutfisobri.soca.data.preference.auth.AuthPreference
import com.lutfisobri.soca.ui.login.LoginActivity
import java.lang.reflect.ParameterizedType

abstract class BaseActivity<T: ViewBinding>: AppCompatActivity() {
    private var _binding: T? = null
    protected val binding get() = _binding!!

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val type = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<T>
        val inflate = type.getMethod("inflate", LayoutInflater::class.java)
        _binding = inflate.invoke(null, layoutInflater) as T

        setContentView(binding.root)
        init()
    }

    abstract fun init()

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    protected fun navTo(clazz: Class<*>) {
        val intent = android.content.Intent(this, clazz)
        startActivity(intent)
    }

    protected fun <T: Parcelable> navTo(clazz: Class<*>, args: T) {
        val intent = android.content.Intent(this, clazz)
        intent.putExtra("args", args)
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
    }

    protected fun <T: Parcelable> getArgs(): T? {
        return intent.getParcelableExtra("args")
    }

    protected fun navToFinish(clazz: Class<*>) {
        navTo(clazz)
        finish()
    }

    private fun navNewTask(clazz: Class<*>) {
        val intent = android.content.Intent(this, clazz)
        intent.flags = android.content.Intent.FLAG_ACTIVITY_NEW_TASK or android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
    }

    protected fun appBar(title: String, menu: Int? = null): Toolbar {
        return findViewById<Toolbar>(R.id.toolbar).apply {
            setTitle(title)
            setNavigationIcon(R.drawable.back)
            setNavigationOnClickListener {
                finish()
            }
            if (menu != null) {
                inflateMenu(menu)
            }
        }
    }

    protected fun <T: BaseResponse> handleError(response: T): Boolean {
        if (response.status == 401) {
            showDialog()
            return true
        }
        return false
    }

    private fun showDialog() {
        val authPreference = AuthPreference(this)
        AlertDialog.Builder(this)
            .setTitle("Error")
            .setMessage("Sesi berakhir, silahkan login kembali")
            .setPositiveButton("OK") { dialog, _ ->
//                authPreference.clearToken()
                dialog.dismiss()
                navNewTask(LoginActivity::class.java)
            }
            .setCancelable(false)
            .show()
    }
}