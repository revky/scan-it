package com.example.scanit.data.repository

import com.example.scanit.domain.repository.BaseApiRepository
import com.example.scanit.domain.repository.RetrofitApiRepository
import com.example.scanit.util.Response
import okhttp3.MultipartBody
import retrofit2.HttpException
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val apiRepository: RetrofitApiRepository
) : BaseApiRepository {
    override suspend fun readProducts(image: MultipartBody.Part): Response<Any> {
        return try {
            val response = apiRepository.readProducts(image)
            val result = response.body()

            if (response.isSuccessful) {
                if (result != null){
                    _productsResponse = Response.Success(result)
                }
                Response.Success(true)
            } else {
                throw HttpException(response)
            }
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    private var _productsResponse: Response<Any> = Response.Loading
    val productsResponse: Response<*>
        get() = _productsResponse
}

