package com.example.scanit.data.repository

import com.example.scanit.domain.repository.BaseAuthRepository
import com.example.scanit.util.Constants.SIGN_IN_REQ
import com.example.scanit.util.Constants.SIGN_UP_REQ
import com.example.scanit.util.Response
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named

class FirebaseAuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private var oneTapClient: SignInClient,
    @Named(SIGN_IN_REQ)
    private var signInRequest: BeginSignInRequest,
    @Named(SIGN_UP_REQ)
    private var signUpRequest: BeginSignInRequest,
    private var signInClient: GoogleSignInClient,
): BaseAuthRepository {
    override fun oneTapSignInWithGoogle() = flow {
        try {
            emit(Response.Loading)
            val signInResult = oneTapClient.beginSignIn(signInRequest).await()
            emit(Response.Success(signInResult))
        } catch (e: Exception) {
            try {
                val signUpResult = oneTapClient.beginSignIn(signUpRequest).await()
                emit(Response.Success(signUpResult))
            } catch (e: Exception) {
                emit(Response.Failure(e))
            }
        }
    }

    override fun firebaseSignInWithGoogle(googleCredential: AuthCredential) = flow {
        try {
            emit(Response.Loading)
            val authResult = auth.signInWithCredential(googleCredential).await()
//            val isNewUser = authResult.additionalUserInfo?.isNewUser ?: false
//            if (isNewUser) {
//                auth.currentUser?.apply {
//                    db.collection(USERS_REF).document(uid).set(toUser()).await()
//                }
//            }
            emit(Response.Success(true))
        } catch (e: Exception) {
            emit(Response.Failure(e))
        }
    }

    override fun signOut() = flow {
        try {
            emit(Response.Loading)
            oneTapClient.signOut().await()
            auth.signOut()
            emit(Response.Success(true))
        } catch (e: Exception) {
            emit(Response.Failure(e))
        }
    }

    override fun revokeAccess() = flow {
        try {
            emit(Response.Loading)
            auth.currentUser?.apply {
                //db.collection(USERS_REF).document(uid).delete().await()
                signInClient.revokeAccess().await()
                oneTapClient.signOut().await()
                delete().await()
            }
            emit(Response.Success(true))
        } catch (e: Exception) {
            emit(Response.Failure(e))
        }
    }
}