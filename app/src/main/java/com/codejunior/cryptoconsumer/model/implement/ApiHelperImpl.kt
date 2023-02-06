package com.codejunior.cryptoconsumer.model.implement

import com.codejunior.cryptoconsumer.network.retrofit.ApiService
import com.codejunior.cryptoconsumer.network.retrofit.model.data.ResponseCrypto
import com.codejunior.cryptoconsumer.network.retrofit.model.information.ResponseDescription
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {
    override suspend fun getListCryptoAPI(): Response<ResponseCrypto> {
        return withContext(Dispatchers.IO) {
            apiService.getListCryptoAPI(15)
        }
    }

    override suspend fun getCryptoInformationAPI(condition: String): Response<ResponseDescription> {
        return withContext(Dispatchers.IO) {
            apiService.getCryptoInformationAPI(condition)
        }
    }

}