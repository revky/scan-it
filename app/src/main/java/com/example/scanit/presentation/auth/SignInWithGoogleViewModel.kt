package com.example.scanit.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scanit.domain.repository.BaseAuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInWithGoogleViewModel @Inject constructor(
    private val repository: BaseAuthRepository
) : ViewModel() {
    fun singInWithGoogle() = viewModelScope.launch {
        repository.signInWithGoogle()
    }
}