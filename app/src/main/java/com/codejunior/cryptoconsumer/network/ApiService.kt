package com.codejunior.cryptoconsumer.network

import com.codejunior.cryptoconsumer.network.model.data.ResponseCrypto
import com.codejunior.cryptoconsumer.network.model.information.ResponseDescription
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v1/cryptocurrency/listings/latest")
 suspend fun getListCryptoAPI(@Query("limit")size:Int) : Response<ResponseCrypto>

    @GET("v2/cryptocurrency/info")
    suspend fun getCryptoInformationAPI(@Query("id")condition:String) : Response<ResponseDescription>

}