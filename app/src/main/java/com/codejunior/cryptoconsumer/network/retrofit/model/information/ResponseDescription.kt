package com.codejunior.cryptoconsumer.network.retrofit.model.information

import com.codejunior.cryptoconsumer.network.retrofit.model.data.Status
import com.google.gson.annotations.SerializedName

/*
To parse the JSON, install kotlin's serialization plugin and do:
val json    = Json { allowStructuredMapKeys = true }
val welcome = json.parse(Welcome.serializer(), jsonString)
*/

data class ResponseDescription(
    @SerializedName("status")
    val status: Status,
    @SerializedName("data")
    val data: Map<String, Datum>
)

data class Datum(
    @SerializedName("id")
    val id: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("logo")
    val logo: String,
    @SerializedName("urls")
    val urls: Urls,
    @SerializedName("date_added")
    val dateAdded: String,
    @SerializedName("twitter_username")
    val twitterUsername: String,
    @SerializedName("is_hidden")
    val isHidden: Long,
    @SerializedName("contract_address")
    val contractAddress: List<ContractAddress>,
)

data class ContractAddress (
    @SerializedName("contract_address")
    val contractAddress: String,
    @SerializedName("platform")
    val platform: Platform
)

data class Platform (
    @SerializedName("name")
    val name: String,

)

data class Urls(
    @SerializedName("website")
    val website: List<String>,
    @SerializedName("twitter")
    val twitter: List<String>,
    @SerializedName("source_code")
    val sourceCode: List<String>,
)

