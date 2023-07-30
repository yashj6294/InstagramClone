package com.example.instagramclone.domain.repository

import com.example.instagramclone.util.Response
import kotlinx.coroutines.flow.Flow

interface AuthenticationRepository {
    fun isUserLoggedIn(): Boolean
    fun getAuthenticationState(): Flow<Boolean>
    fun signIn(email: String, password: String): Flow<Response<Boolean>>
    fun signOut(): Flow<Response<Boolean>>
    fun signUp(email: String, password: String, userName: String): Flow<Response<Boolean>>
}