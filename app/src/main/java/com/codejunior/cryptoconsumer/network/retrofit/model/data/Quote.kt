package com.codejunior.cryptoconsumer.network.retrofit.model.data

import com.google.gson.annotations.SerializedName

data class Quote(
    @SerializedName("USD")
    val usd: Usd? = null
)
