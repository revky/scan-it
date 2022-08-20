package com.example.scanit.domain.repository

import com.example.scanit.util.Response
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.firebase.auth.AuthCredential
import kotlinx.coroutines.flow.Flow

interface BaseAuthRepository {
    fun oneTapSignInWithGoogle(): Flow<Response<BeginSignInResult>>

    fun firebaseSignInWithGoogle(googleCredential: AuthCredential): Flow<Response<Boolean>>

    fun signOut(): Flow<Response<Boolean>>

    fun revokeAccess(): Flow<Response<Boolean>>
}