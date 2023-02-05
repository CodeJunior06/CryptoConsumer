package com.codejunior.cryptoconsumer.network.model.information

import com.codejunior.cryptoconsumer.network.model.data.Status
import com.google.gson.annotations.SerializedName

/*
To parse the JSON, install kotlin's serialization plugin and do:
val json    = Json { allowStructuredMapKeys = true }
val welcome = json.parse(Welcome.serializer(), jsonString)
*/

data class ResponseDescription(
    val status: Status,
    val data: Map<String, Datum>
)

data class Datum (
    val id: Long,
    val name: String,
    val symbol: String,
    val category: String,
    val description: String,
    val slug: String,
    val logo: String,
    val subreddit: String,
    val notice: String,
    val tags: List<String>,

    @SerializedName("tag-names")
    val tagNames: List<String>,

    @SerializedName("tag-groups")
    val tagGroups: List<String>,

    val urls: Urls,
    val platform: String? = null,

    @SerializedName("date_added")
    val dateAdded: String,

    @SerializedName("twitter_username")
    val twitterUsername: String,

    @SerializedName("is_hidden")
    val isHidden: Long,

    @SerializedName("date_launched")
    val dateLaunched: String? = null,

    @SerializedName("contract_address")
    val contractAddress: List<ContractAddress>,

    @SerializedName("self_reported_circulating_supply")
    val selfReportedCirculatingSupply: String? = null,

    @SerializedName("self_reported_tags")
    val selfReportedTags: String? = null,

    @SerializedName("self_reported_market_cap")
    val selfReportedMarketCap: String? = null
)

data class ContractAddress (
    @SerializedName("contract_address")
    val contractAddress: String,

    val platform: Platform
)

data class Platform (
    val name: String,
    val coin: Coin
)

data class Coin (
    val id: String,
    val name: String,
    val symbol: String,
    val slug: String
)

data class Urls (
    val website: List<String>,
    val twitter: List<String>,

    @SerializedName("message_board")
    val messageBoard: List<String>,

    val chat: List<String>,
    val facebook: List<String>,
    val explorer: List<String>,
    val reddit: List<String>,

    @SerializedName("technical_doc")
    val technicalDoc: List<String>,

    @SerializedName("source_code")
    val sourceCode: List<String>,

    val announcement: List<String>
)

