package com.codejunior.cryptoconsumer.network.model.data

import com.google.gson.annotations.SerializedName

data class Status(
    @SerializedName("timestamp")
    val timestamp: String? = null,

    @SerializedName("error_code")
    val errorCode: Long,

    @SerializedName("error_message")
    val errorMessage: String? = null,

    @SerializedName("elapsed")
    val elapsed: Long? = null,

    @SerializedName("credit_count")
    val creditCount: Long? = null,

    @SerializedName("notice")
    val notice: String? = null,

    @SerializedName("total_count")
    val totalCount: Long = 0
)
