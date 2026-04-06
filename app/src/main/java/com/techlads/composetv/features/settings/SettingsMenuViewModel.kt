package com.techlads.composetv.features.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techlads.auth.AuthState
import com.techlads.auth.UserSession
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsMenuViewModel @Inject constructor(
    private val userSession: UserSession
) : ViewModel() {
    val authState: StateFlow<AuthState> = userSession.authState

    private val _isLoggingOut = MutableStateFlow(false)
    val isLoggingOut = _isLoggingOut.asStateFlow()

    fun logout() {
        if (_isLoggingOut.value) return
        viewModelScope.launch {
            _isLoggingOut.value = true
            userSession.logout()
            _isLoggingOut.value = false
        }
    }
}
