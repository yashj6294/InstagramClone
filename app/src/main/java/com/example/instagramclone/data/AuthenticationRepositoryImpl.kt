package com.example.instagramclone.data

import com.example.instagramclone.domain.models.User
import com.example.instagramclone.domain.repository.AuthenticationRepository
import com.example.instagramclone.util.Constants
import com.example.instagramclone.util.Response
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore
) : AuthenticationRepository {
    private var operationSuccesful: Boolean = false
    override fun isUserLoggedIn(): Boolean {
        return firebaseAuth.currentUser != null
    }

    override fun getAuthenticationState(): Flow<Boolean> = callbackFlow {
        val authStateListener = FirebaseAuth.AuthStateListener {
            trySend(it.currentUser == null)
        }
        firebaseAuth.addAuthStateListener(authStateListener)
        awaitClose {
            firebaseAuth.removeAuthStateListener(authStateListener)
        }
    }

    override fun signIn(email: String, password: String): Flow<Response<Boolean>> = flow {
        operationSuccesful = false;
        try {
            emit(Response.Loading)
            val data = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            operationSuccesful = data.user?.uid != null
            emit(Response.Success(operationSuccesful))
        } catch (e: Exception) {
            emit(Response.Failure(message = e.localizedMessage ?: "An Unexpected error"))
        }
    }

    override fun signOut(): Flow<Response<Boolean>> = flow {
        try {
            emit(Response.Loading)
            firebaseAuth.signOut()
            emit(Response.Success(true))
        } catch (e: Exception) {
            emit(Response.Failure(message = e.localizedMessage ?: "An Unexpected error"))
        }
    }

    override fun signUp(
        email: String,
        password: String,
        userName: String
    ): Flow<Response<Boolean>> = flow {
        operationSuccesful = false;
        try {
            emit(Response.Loading)
            val data = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            operationSuccesful = data.user?.uid != null
            if (operationSuccesful) {
                val userId = firebaseAuth.currentUser?.uid!!
                val userObj = User(
                    userName = userName,
                    email = email,
                    userId = userId
                )
                firebaseFirestore.collection(Constants.USERS_COLLECTION).document(userId)
                    .set(userObj)
                emit(Response.Success(operationSuccesful))
            } else {
                emit(Response.Success(operationSuccesful))
            }
            emit(Response.Success(operationSuccesful))
        } catch (e: Exception) {
            emit(Response.Failure(message = e.localizedMessage ?: "An Unexpected error"))
        }
    }
}