package com.example.instagramclone.domain.use_cases

import com.example.instagramclone.domain.repository.AuthenticationRepository
import javax.inject.Inject

class SignUp @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) {
    operator fun invoke(email: String, password: String, userName: String) =
        authenticationRepository.signUp(email, password, userName)
}