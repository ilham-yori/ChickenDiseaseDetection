package com.midas.midaschick.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.midas.midaschick.model.UserModel
import com.midas.midaschick.model.UserPreference

class SplashViewModel (private val pref: UserPreference): ViewModel() {

    fun getUser(): LiveData<UserModel> {
        return pref.getUser().asLiveData()
    }
}