package com.codejunior.cryptoconsumer.extension

import com.codejunior.cryptoconsumer.network.retrofit.model.data.Crypto
import com.codejunior.cryptoconsumer.network.retrofit.model.data.ResponseCrypto
import com.codejunior.cryptoconsumer.network.retrofit.model.information.Datum
import com.codejunior.cryptoconsumer.network.room.entities.CryptoEntity

fun ResponseCrypto.render(
    crypto: Crypto,
    modelDescription: Datum,
    logoBase64: String
): CryptoEntity {

    return CryptoEntity(
        id = crypto.id!!,
        name = crypto.name,
        symbol = crypto.symbol,
        slug = crypto.slug,
        dateAddCoinMarket = crypto.dateAdded,
        supplyMax = crypto.maxSupply,
        supplyCirculation = crypto.circulatingSupply,
        rankList = crypto.cmcRank!!,
        price = crypto.quote!!.usd!!.price,
        percent1H = crypto.quote.usd!!.percentChange1H,
        percent24H = crypto.quote.usd.percentChange24H,
        percent7D = crypto.quote.usd.percentChange7D,
        percent30D = crypto.quote.usd.percentChange30D,
        percent60D = crypto.quote.usd.percentChange60D,
        percent90D = crypto.quote.usd.percentChange90D,
        marketCap = crypto.quote.usd.marketCap,
        dominance = crypto.quote.usd.marketCapDominance,
        category = modelDescription.category,
        description = modelDescription.description,
        logo = modelDescription.logo,
        webSite = modelDescription.urls.website[0],
        numberContract = modelDescription.contractAddress.size,
        logoBase64 = logoBase64
    )

}