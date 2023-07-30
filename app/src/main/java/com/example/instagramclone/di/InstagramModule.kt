package com.example.instagramclone.di

import com.example.instagramclone.data.AuthenticationRepositoryImpl
import com.example.instagramclone.domain.repository.AuthenticationRepository
import com.example.instagramclone.domain.use_cases.AuthState
import com.example.instagramclone.domain.use_cases.AuthenticationUseCases
import com.example.instagramclone.domain.use_cases.IsUserAuthenticated
import com.example.instagramclone.domain.use_cases.SignIn
import com.example.instagramclone.domain.use_cases.SignOut
import com.example.instagramclone.domain.use_cases.SignUp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InstagramModule {

    @Singleton
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Singleton
    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Singleton
    @Provides
    fun provideFirebaseStorage(): FirebaseStorage {
        return FirebaseStorage.getInstance()
    }

    @Singleton
    @Provides
    fun provideAuthenticationRepository(
        auth: FirebaseAuth,
        firestore: FirebaseFirestore
    ): AuthenticationRepository {
        return AuthenticationRepositoryImpl(auth, firestore)
    }

    @Singleton
    @Provides
    fun provideAuthUseCases(repository: AuthenticationRepository): AuthenticationUseCases {
        return AuthenticationUseCases(
            isUserAuthenticated = IsUserAuthenticated(repository),
            signIn = SignIn(repository),
            signOut = SignOut(repository),
            signUp = SignUp(repository),
            authState = AuthState(repository),
        )
    }
}