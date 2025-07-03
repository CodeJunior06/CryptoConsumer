package com.codejunior.cryptoconsumer.network.retrofit.model.data

import com.google.gson.annotations.SerializedName

data class Usd(

    @SerializedName("price")
    val price: Double,

    @SerializedName("volume_24h")
    val volume24H: String? = null,

    @SerializedName("volume_change_24h")
    val volumeChange24H: String? = null,

    @SerializedName("percent_change_1h")
    val percentChange1H: Double? = null,

    @SerializedName("percent_change_24h")
    val percentChange24H: Double? = null,

    @SerializedName("percent_change_7d")
    val percentChange7D: Double? = null,

    @SerializedName("percent_change_30d")
    val percentChange30D: Double? = null,

    @SerializedName("percent_change_60d")
    val percentChange60D: Double? = null,

    @SerializedName("percent_change_90d")
    val percentChange90D: Double? = null,

    @SerializedName("market_cap")
    val marketCap: Double? = null,

    @SerializedName("market_cap_dominance")
    val marketCapDominance: Double,

    @SerializedName("fully_diluted_market_cap")
    val fullyDilutedMarketCap: String? = null,

    @SerializedName("tvl")
    val tvl: Double? = null,

    @SerializedName("last_updated")
    val lastUpdated: String? = null
)
