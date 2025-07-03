package com.codejunior.cryptoconsumer.network.room.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "crypto")
@Parcelize
data class CryptoEntity(
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "crypto_id") val id: Long,
    @ColumnInfo(name = "crypto_name") val name: String?,
    @ColumnInfo(name = "crypto_symbol") val symbol: String,
    @ColumnInfo(name = "crypto_date_add") val dateAddCoin: String,
    @ColumnInfo(name = "crypto_supply_max") val supplyMax: Double,
    @ColumnInfo(name = "crypto_supply_circulation") val supplyCirculation: Double,
    @ColumnInfo(name = "crypto_is_infinite") val isInfinite: Boolean,
    @ColumnInfo(name = "crypto_rank") val rankList: Int,
    @ColumnInfo(name = "crypto_price") val price: Double,
    @ColumnInfo(name = "crypto_percent_1h") val percent1H: Double?,
    @ColumnInfo(name = "crypto_percent_24h") val percent24H: Double?,
    @ColumnInfo(name = "crypto_percent_7d") val percent7D: Double?,
    @ColumnInfo(name = "crypto_percent_30d") val percent30D: Double?,
    @ColumnInfo(name = "crypto_percent_60d") val percent60D: Double?,
    @ColumnInfo(name = "crypto_percent_90d") val percent90D: Double?,
    @ColumnInfo(name = "crypto_market_cap") val marketCap: Double?,
    @ColumnInfo(name = "crypto_dominance") val dominance: Double?,
    @ColumnInfo(name = "crypto_description") val description: String,
    @ColumnInfo(name = "crypto_logo_path") val logoPath: String,
    @ColumnInfo(name = "crypto_webSite") val webSite: String?,
) : Parcelable
