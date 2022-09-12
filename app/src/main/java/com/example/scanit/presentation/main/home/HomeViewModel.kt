package com.example.scanit.presentation.main.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scanit.data.repository.ApiRepositoryImpl
import com.example.scanit.domain.model.ProductApi
import com.example.scanit.domain.repository.BaseAuthRepository
import com.example.scanit.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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

    private val _imageUploadState: MutableStateFlow<Response<List<ProductApi>>> = MutableStateFlow(Response.Loading)
    val uploadImageState: StateFlow<Response<List<ProductApi>>>
        get() = _imageUploadState

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

    //TODO jakoś się da zwrócić bezpośrednio ze scope'a ale to jest tricky i nie pamiętam
    fun uploadImage(file: File): Boolean {
        var result = false
        viewModelScope.launch {
            result = apiRepository.uploadImage(file)
        }
        return result
    }
}