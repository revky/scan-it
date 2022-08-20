package com.example.scanit.data.repository

import com.example.scanit.domain.repository.BaseAuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class FirebaseAuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth
): BaseAuthRepository {
    override suspend fun signInWithGoogle(): FirebaseUser? {
       return auth.currentUser
    }

    override suspend fun signOut(): FirebaseUser? {
        auth.signOut()
        return auth.currentUser
    }

    override suspend fun getUser(): FirebaseUser? {
        return auth.currentUser
    }

}