package com.example.instagramclone.presentation.auth

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.instagramclone.domain.use_cases.AuthenticationUseCases
import com.example.instagramclone.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val authenticationUseCases: AuthenticationUseCases
) : ViewModel() {
    val isUserAuthenticated get() = authenticationUseCases.isUserAuthenticated()
    private val _signInState = mutableStateOf<Response<Boolean>>(Response.Success(false))
    val signInState: State<Response<Boolean>> = _signInState

    private val _signUpState = mutableStateOf<Response<Boolean>>(Response.Success(false))
    val signUpState: State<Response<Boolean>> = _signUpState

    private val _signOutState = mutableStateOf<Response<Boolean>>(Response.Success(false))
    val signOutState: State<Response<Boolean>> = _signOutState

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            authenticationUseCases.signIn(email, password).collect {
                _signInState.value = it
            }
        }
    }

    private val _authState = mutableStateOf<Boolean>(false)
    val authState: State<Boolean> = _authState

    fun signUp(email: String, password: String, userName: String) {
        viewModelScope.launch {
            authenticationUseCases.signUp(email, password, userName).collect {
                _signUpState.value = it
            }
        }
    }

    fun getFirebaseAuthState() {
        viewModelScope.launch {
            authenticationUseCases.authState().collect {
                _authState.value = it
            }
        }
    }

    fun signOut() {
        viewModelScope.launch {
            authenticationUseCases.signOut().collect {
                _signOutState.value = it
                if (it == Response.Success(true)) {
                    _signInState.value = Response.Success(false)
                }
            }
        }
    }
}