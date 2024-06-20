package com.lutfisobri.soca.ui.splash

import android.annotation.SuppressLint
import androidx.lifecycle.lifecycleScope
import com.lutfisobri.soca.data.preference.auth.AuthPreference
import com.lutfisobri.soca.databinding.ActivitySplashScreenBinding
import com.lutfisobri.soca.ui.BaseActivity
import com.lutfisobri.soca.ui.dashboard.DashboardActivity
import com.lutfisobri.soca.ui.login.LoginActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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