package com.techlads.composetv.navigation

import androidx.lifecycle.ViewModel
import com.techlads.auth.UserSession
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppNavigationViewModel @Inject constructor(
    userSession: UserSession,
) : ViewModel() {
    val authState = userSession.authState
}
