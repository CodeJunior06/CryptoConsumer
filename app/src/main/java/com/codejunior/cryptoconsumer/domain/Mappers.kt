package com.codejunior.cryptoconsumer.domain

import android.content.Context
import com.codejunior.cryptoconsumer.R
import com.codejunior.cryptoconsumer.domain.model.Crypto
import com.codejunior.cryptoconsumer.network.retrofit.model.data.CryptoDto
import com.codejunior.cryptoconsumer.network.room.entities.CryptoEntity


fun CryptoEntity.toDomain() = Crypto (
    id = id,
    name = name ?: "",
    description = description,
    symbol = symbol,
    currentSupply = supplyCirculation,
    maxSupply = supplyMax,
    isInfiniteSupply = isInfinite,
    dateAdd = dateAddCoin,
    price = price,
    percent1H = percent1H,
    percent1D = percent24H,
    percent1S = percent7D,
    marketCap = marketCap,
    rank = rankList,
    routePhoto =  logoPath
)

fun List<CryptoEntity>.fromEntityToDomain(): List<Crypto> = map { it.toDomain() }

fun Context.codeAppToMessage(codeApp: CodeApp) : String {
   return when(codeApp){
        CodeApp.ErrDataBase -> getString(R.string.app_name)
        CodeApp.ErrNotContainData -> getString(R.string.app_name)
        CodeApp.ErrNotInternet -> getString(R.string.app_name)
        is CodeApp.ErrService -> codeApp.err
        CodeApp.FetchCrypto -> getString(R.string.app_name)
    }
}