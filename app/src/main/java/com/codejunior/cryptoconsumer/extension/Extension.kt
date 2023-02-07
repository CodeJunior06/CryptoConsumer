package com.codejunior.cryptoconsumer.extension

import com.codejunior.cryptoconsumer.network.retrofit.model.data.Crypto
import com.codejunior.cryptoconsumer.network.retrofit.model.data.ResponseCrypto
import com.codejunior.cryptoconsumer.network.retrofit.model.information.Datum
import com.codejunior.cryptoconsumer.network.room.entities.CryptoEntity
import com.codejunior.cryptoconsumer.utils.Defines

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
        dateAddCoinMarket = crypto.dateAdded!!.substring(0,10),
        supplyMax = crypto.maxSupply,
        supplyCirculation = crypto.circulatingSupply,
        rankList = crypto.cmcRank!!,
        price = Defines.toConverter(crypto.quote!!.usd!!.price!!),
        percent1H = Defines.toConverter(crypto.quote.usd!!.percentChange1H!!),
        percent24H = Defines.toConverter(crypto.quote.usd.percentChange24H!!),
        percent7D = Defines.toConverter(crypto.quote.usd.percentChange7D!!),
        percent30D = Defines.toConverter(crypto.quote.usd.percentChange30D!!),
        percent60D = Defines.toConverter(crypto.quote.usd.percentChange60D!!),
        percent90D =Defines.toConverter( crypto.quote.usd.percentChange90D!!),
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