package com.midas.midaschick.ui.splash

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowInsets
import android.view.WindowManager
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.midas.midaschick.databinding.ActivitySplashBinding
import com.midas.midaschick.model.UserPreference
import com.midas.midaschick.ui.ViewModelFactory
import com.midas.midaschick.ui.main.MainActivity
import com.midas.midaschick.ui.onboarding.OnBoardingActivity

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupViewModel()

        val tigaDetik : Long = 3000
        val loop = Handler(Looper.getMainLooper())

        splashViewModel.getUser().observe(this) { user ->
            if (user.isState) {
                loop.postDelayed({
                    val move = Intent(this@SplashActivity, MainActivity::class.java)
                    startActivity(move)
                    finish()
                }, tigaDetik)
            }else{
                loop.postDelayed({
                    val move = Intent(this@SplashActivity, OnBoardingActivity::class.java)
                    startActivity(move)
                    finish()
                }, tigaDetik)
            }
        }
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private fun setupViewModel() {
        splashViewModel = ViewModelProvider(
            this,
            ViewModelFactory(UserPreference.getInstance(dataStore))
        )[SplashViewModel::class.java]
    }
}