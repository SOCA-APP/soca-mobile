package com.lutfisobri.soca.ui.splash

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.lutfisobri.soca.R

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : BaseActivity<ActivitySplashScreenBinding>() {
    override fun init() {
        lifecycleScope.launch {
            delay(3000)
            if (isLogin())
                navToFinish(DashboardActivity::class.java)
            else
            navToFinish(LoginActivity::class.java)
        }
    }

    private fun isLogin(): Boolean {
        return AuthPreference(this).getToken() != null
    }
}