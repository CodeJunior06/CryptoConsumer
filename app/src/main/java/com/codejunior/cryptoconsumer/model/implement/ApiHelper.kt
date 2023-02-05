package com.codejunior.cryptoconsumer.model.implement

import com.codejunior.cryptoconsumer.network.model.data.ResponseCrypto
import com.codejunior.cryptoconsumer.network.model.information.ResponseDescription
import retrofit2.Response

interface ApiHelper {
    suspend fun  getListCryptoAPI() : Response<ResponseCrypto>
    suspend fun  getCryptoInformationAPI(condition:String) : Response<ResponseDescription>
}