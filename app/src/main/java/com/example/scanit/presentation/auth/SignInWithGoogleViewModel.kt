package com.example.scanit.presentation.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scanit.domain.repository.BaseAuthRepository
import com.example.scanit.util.Response
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.AuthCredential
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInWithGoogleViewModel @Inject constructor(
    private val repository: BaseAuthRepository,
    val oneTapClient: SignInClient
) : ViewModel() {
    var oneTapSignInResponse by mutableStateOf<Response<BeginSignInResult>>(Response.Success(null))
        private set
    var signInWithGoogleResponse by mutableStateOf<Response<Boolean>>(Response.Success(false))
        private set

    fun oneTapSignIn() = viewModelScope.launch {
        repository.oneTapSignInWithGoogle().collect {
            oneTapSignInResponse = it
        }
    }

    fun signInWithGoogle(googleCredential: AuthCredential) = viewModelScope.launch {
        repository.firebaseSignInWithGoogle(googleCredential).collect {
            signInWithGoogleResponse = it
        }
    }
}