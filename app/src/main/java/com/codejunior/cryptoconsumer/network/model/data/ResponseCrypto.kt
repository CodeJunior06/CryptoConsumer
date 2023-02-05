package com.codejunior.cryptoconsumer.network.model.data
import com.google.gson.annotations.SerializedName

data class ResponseCrypto(
    @SerializedName("status")
    val status: Status,
    @SerializedName("data")
    val data: List<Crypto>
)
