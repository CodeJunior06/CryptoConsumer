package com.codejunior.cryptoconsumer.network.model.data

import com.google.gson.annotations.SerializedName

data class Quote(
    @SerializedName("USD")
    val usd: Usd? = null
)
