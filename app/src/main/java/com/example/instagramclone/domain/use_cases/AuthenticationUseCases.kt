package com.example.instagramclone.domain.use_cases

data class AuthenticationUseCases(
    val isUserAuthenticated: IsUserAuthenticated,
    val signIn: SignIn,
    val signOut: SignOut,
    val signUp: SignUp,
    val authState: AuthState
)
