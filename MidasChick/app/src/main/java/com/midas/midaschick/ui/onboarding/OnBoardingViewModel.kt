package com.midas.midaschick.ui.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.midas.midaschick.model.UserModel
import com.midas.midaschick.model.UserPreference
import kotlinx.coroutines.launch

class OnBoardingViewModel (private val pref: UserPreference): ViewModel() {
    fun saveUser(user: UserModel) {
        viewModelScope.launch {
            pref.saveUser(user)
        }
    }
}