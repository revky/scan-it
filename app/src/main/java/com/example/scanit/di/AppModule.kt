package com.example.scanit.di

import com.example.scanit.data.repository.FirebaseAuthRepositoryImpl
import com.example.scanit.domain.repository.BaseAuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideFirebaseGoogleAuth() = Firebase.auth

    @Singleton
    @Provides
    fun provideFirebaseFirestore() = Firebase.firestore

    @Singleton
    @Provides
    fun provideAuthRepository(
        auth: FirebaseAuth
    ): BaseAuthRepository {
        return FirebaseAuthRepositoryImpl(auth)
    }

    @Provides
    fun provideCurrentUser(
        auth: FirebaseAuth
    ): FirebaseUser? = auth.currentUser
}