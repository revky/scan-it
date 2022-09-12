package com.example.scanit.presentation.main.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scanit.data.repository.ApiRepositoryImpl
import com.example.scanit.domain.repository.BaseAuthRepository
import com.example.scanit.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: BaseAuthRepository,
    private var apiRepository: ApiRepositoryImpl

) : ViewModel() {
    private var signOutResponse by mutableStateOf<Response<Boolean>>(Response.Success(false))
    private var revokeAccessResponse by mutableStateOf<Response<Boolean>>(Response.Success(false))
    var uploadImageStateVM = apiRepository.imageUploadState

    fun signOut() = viewModelScope.launch {
        repository.signOut().collect {
            signOutResponse = it
        }
    }

    fun revokeAccess() = viewModelScope.launch {
        repository.revokeAccess().collect {
            revokeAccessResponse = it
        }
    }

    fun uploadImage(file: File) = viewModelScope.launch {
        apiRepository.uploadImage(file).collect {
            uploadImageStateVM.value = it
            apiRepository.imageUploadState.value = it
            Log.e("tag",apiRepository.imageUploadState.value.toString())
        }
    }
}