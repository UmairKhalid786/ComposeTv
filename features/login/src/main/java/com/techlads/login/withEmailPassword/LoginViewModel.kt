package com.techlads.login.withEmailPassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techlads.login.data.TmdbLoginRepository
import com.techlads.network.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: TmdbLoginRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    fun login(username: String, password: String) {
        if (_uiState.value.isLoading) return

        val normalizedUsername = username.trim()
        if (normalizedUsername.isBlank() || password.isBlank()) {
            _uiState.update { it.copy(errorMessage = "Enter your TMDB username and password.") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            when (val result = repository.login(normalizedUsername, password)) {
                is ApiResult.Success -> _uiState.value = LoginUiState()
                is ApiResult.Error -> _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = result.message,
                    )
                }
            }
        }
    }

    fun clearError() {
        _uiState.update { current ->
            if (current.errorMessage == null) current else current.copy(errorMessage = null)
        }
    }
}

data class LoginUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)
