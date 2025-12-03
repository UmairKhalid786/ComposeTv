package com.techlads.composetv.features.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techlads.auth.UserSession
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsMenuViewModel @Inject constructor(
    private val userSession: UserSession
) : ViewModel() {

    fun logout() {
        viewModelScope.launch {
            userSession.logout()
        }
    }
}