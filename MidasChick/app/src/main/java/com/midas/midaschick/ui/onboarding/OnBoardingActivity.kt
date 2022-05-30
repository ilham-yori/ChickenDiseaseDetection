package com.midas.midaschick.ui.onboarding

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.midas.midaschick.R
import com.midas.midaschick.databinding.ActivityOnBoardingBinding
import com.midas.midaschick.databinding.ActivitySplashBinding
import com.midas.midaschick.model.UserModel
import com.midas.midaschick.model.UserPreference
import com.midas.midaschick.ui.ViewModelFactory
import com.midas.midaschick.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_on_boarding.*

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class OnBoardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnBoardingBinding
    private lateinit var onBoardingViewModel: OnBoardingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        button()
        setupViewModel()

        binding.button.setOnClickListener {
            val model = UserModel(true)
            onBoardingViewModel.saveUser(model)
            val move = Intent(this@OnBoardingActivity, MainActivity::class.java)
            startActivity(move)
            finish()
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

    private fun button() {
        button.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun setupViewModel() {
        onBoardingViewModel = ViewModelProvider(
            this,
            ViewModelFactory(UserPreference.getInstance(dataStore))
        )[OnBoardingViewModel::class.java]
    }
}