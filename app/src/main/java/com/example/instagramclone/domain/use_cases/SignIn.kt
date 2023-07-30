package com.example.instagramclone.domain.use_cases

import com.example.instagramclone.domain.repository.AuthenticationRepository
import javax.inject.Inject

class SignIn @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) {
    operator fun invoke(email: String, password: String) =
        authenticationRepository.signIn(email, password)
}