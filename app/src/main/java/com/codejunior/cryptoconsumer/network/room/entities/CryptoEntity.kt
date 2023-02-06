package com.codejunior.cryptoconsumer.network.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.codejunior.cryptoconsumer.network.retrofit.model.data.Crypto
import com.codejunior.cryptoconsumer.network.retrofit.model.data.ResponseCrypto
import com.codejunior.cryptoconsumer.network.retrofit.model.information.Datum
import com.codejunior.cryptoconsumer.network.retrofit.model.information.ResponseDescription

@Entity(tableName = "crypto")
data class CryptoEntity (

    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "crypto_id") val id: Long,
    @ColumnInfo(name = "crypto_name") val name: String? ,
    @ColumnInfo(name = "crypto_symbol") val symbol: String? ,
    @ColumnInfo(name = "crypto_slug") val slug: String? ,
    @ColumnInfo(name = "crypto_date_add") val dateAddCoinMarket: String?,
    @ColumnInfo(name = "crypto_supply_max") val supplyMax: String? ,
    @ColumnInfo(name = "crypto_supply_circulation") val supplyCirculation: String?,
    @ColumnInfo(name = "crypto_rank") val rankList: Int,
    @ColumnInfo(name = "crypto_price") val price: String?,
    @ColumnInfo(name = "crypto_percent_1h") val percent1H: String?,
    @ColumnInfo(name = "crypto_percent_24h") val percent24H: String?,
    @ColumnInfo(name = "crypto_percent_7d") val percent7D: String?,
    @ColumnInfo(name = "crypto_percent_30d") val percent30D: String?,
    @ColumnInfo(name = "crypto_percent_60d") val percent60D: String?,
    @ColumnInfo(name = "crypto_percent_90d") val percent90D: String?,
    @ColumnInfo(name = "crypto_market_cap") val marketCap: String?,
    @ColumnInfo(name = "crypto_dominance") val dominance: String?,
    @ColumnInfo(name = "crypto_category") val category: String?,
    @ColumnInfo(name = "crypto_description") val description: String?,
    @ColumnInfo(name = "crypto_logo") val logo: String?,
    @ColumnInfo(name = "crypto_logo_base64") val logoBase64: String?,
    @ColumnInfo(name = "crypto_webSite") val webSite: String?,
    @ColumnInfo(name = "crypto_number_contract") val numberContract: Int,
    )
