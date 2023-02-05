package com.codejunior.cryptoconsumer.network.model.data

import com.google.gson.annotations.SerializedName

data class Usd(

    @SerializedName("price")
    val price: String? = null,

    @SerializedName("volume_24h")
    val volume24H: String? = null,

    @SerializedName("volume_change_24h")
    val volumeChange24H: String? = null,

    @SerializedName("percent_change_1h")
    val percentChange1H: String? = null,

    @SerializedName("percent_change_24h")
    val percentChange24H: String? = null,

    @SerializedName("percent_change_7d")
    val percentChange7D: String? = null,

    @SerializedName("percent_change_30d")
    val percentChange30D: String? = null,

    @SerializedName("percent_change_60d")
    val percentChange60D: String? = null,

    @SerializedName("percent_change_90d")
    val percentChange90D: String? = null,

    @SerializedName("market_cap")
    val marketCap: String? = null,

    @SerializedName("market_cap_dominance")
    val marketCapDominance: String? = null,

    @SerializedName("fully_diluted_market_cap")
    val fullyDilutedMarketCap: String? = null,

    @SerializedName("tvl")
    val tvl: Double? = null,

    @SerializedName("last_updated")
    val lastUpdated: String? = null
)
