package com.lutfisobri.soca.ui.splash

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.lutfisobri.soca.R
import com.lutfisobri.soca.databinding.ActivitySplashScreenBinding
import com.lutfisobri.soca.ui.BaseActivity
import com.lutfisobri.soca.ui.dashboard.DashboardActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : BaseActivity<ActivitySplashScreenBinding>() {
    override fun init() {
        lifecycleScope.launch {
            delay(3000)
            navToFinish(DashboardActivity::class.java)
        }
    }
}