package com.codejunior.cryptoconsumer.model.implement

import com.codejunior.cryptoconsumer.network.retrofit.model.data.ResponseCrypto
import com.codejunior.cryptoconsumer.network.retrofit.model.information.ResponseDescription
import retrofit2.Response

interface ApiHelper {
    suspend fun  getListCryptoAPI() : Response<ResponseCrypto>
    suspend fun  getCryptoInformationAPI(condition:String) : Response<ResponseDescription>
}