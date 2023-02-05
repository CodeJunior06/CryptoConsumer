package com.codejunior.cryptoconsumer.network.model.data

import com.google.gson.annotations.SerializedName

data class Crypto (

    @SerializedName("id")
    val id: Long? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("symbol")
    val symbol: String? = null,
    @SerializedName("slug")
    val slug: String? = null,

    @SerializedName("num_market_pairs")
    val numMarketPairs: Long? = null,

    @SerializedName("date_added")
    val dateAdded: String? = null,

    @SerializedName("tags")
    val tags: List<String>? = null,

    @SerializedName("max_supply")
    val maxSupply: Long? = null,

    @SerializedName("circulating_supply")
    val circulatingSupply: String? = null,

    @SerializedName("total_supply")
    val totalSupply: String? = null,

    @SerializedName("platform")
    val platform: Platform? = null,

    @SerializedName("cmc_rank")
    val cmcRank: Int? = null,

    @SerializedName("self_reported_circulating_supply")
    val selfReportedCirculatingSupply: String? = null,

    @SerializedName("self_reported_market_cap")
    val selfReportedMarketCap: String? = null,

    @SerializedName("tvl_ratio")
    val tvlRatio: String? = null,

    @SerializedName("last_updated")
    val lastUpdated: String? = null,

    @SerializedName("quote")
    val quote: Quote? = null
        )
