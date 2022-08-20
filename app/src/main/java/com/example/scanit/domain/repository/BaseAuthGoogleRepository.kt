package com.example.scanit.domain.repository

import com.google.firebase.auth.FirebaseUser

interface BaseAuthRepository {

    suspend fun signInWithGoogle(): FirebaseUser?

    suspend fun signOut(): FirebaseUser?

    suspend fun getUser(): FirebaseUser?
}