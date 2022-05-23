package com.midas.midaschick.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.midas.midaschick.model.UserPreference
import com.midas.midaschick.ui.onboarding.OnBoardingViewModel
import com.midas.midaschick.ui.splash.SplashViewModel

class ViewModelFactory (private val pref: UserPreference) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SplashViewModel::class.java) -> {
                SplashViewModel(pref) as T
            }
            modelClass.isAssignableFrom(OnBoardingViewModel::class.java) -> {
                OnBoardingViewModel(pref) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}