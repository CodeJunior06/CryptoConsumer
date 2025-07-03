package com.codejunior.cryptoconsumer.model.implement

import android.content.Context
import com.codejunior.cryptoconsumer.domain.CodeApp
import com.codejunior.cryptoconsumer.domain.fromEntityToDomain
import com.codejunior.cryptoconsumer.domain.model.Crypto
import com.codejunior.cryptoconsumer.network.ResponseGeneric
import com.codejunior.cryptoconsumer.network.retrofit.ApiService
import com.codejunior.cryptoconsumer.network.retrofit.model.data.CryptoDto
import com.codejunior.cryptoconsumer.network.retrofit.model.information.Datum
import com.codejunior.cryptoconsumer.network.room.dao.CryptoDao
import com.codejunior.cryptoconsumer.network.room.entities.CryptoEntity
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.net.URL
import javax.inject.Inject


class ApiServiceImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val apiService: ApiService,
    private val roomService: CryptoDao
) : ApiHelper {
    override suspend fun fetchCrypto(): ResponseGeneric<List<Crypto>> {
        apiService.getListCryptoAPI(15).also { res ->
            return res.body()?.let {
                if (res.isSuccessful) {
                    fetchCryptoInformation(it.data)
                } else fallbackFromDb(it.status.errorMessage ?: "")
            } ?: ResponseGeneric.Error(CodeApp.ErrNotContainData)
        }
    }

    private suspend fun fallbackFromDb(message: String = ""): ResponseGeneric<List<Crypto>> {
        return try {
            val local = roomService.getAllCrypto()
            if (local.isNotEmpty()) ResponseGeneric.Success(local.fromEntityToDomain())
            else ResponseGeneric.Error(CodeApp.ErrService(message))
        } catch (e: Exception) {
            ResponseGeneric.Error(CodeApp.ErrDataBase)
        }
    }

    private suspend fun fetchCryptoInformation(lst: List<CryptoDto>): ResponseGeneric<List<Crypto>> {
        apiService.getCryptoInformationAPI(lst.joinToString(",") { it.id.toString() }).also { res ->
            return res.body()?.let { description ->
                if (res.isSuccessful) {
                    description.data.forEach {
                        val path = saveImageFromUrl(it.value.logo, it.key)?.absolutePath ?: ""
                        val modelDto = lst.findLast { dto -> dto.id.toString() == it.key }
                        roomService.insertCrypto(setModelEntity(it.value, path, modelDto!!))
                    }
                    fallbackFromDb()
                } else ResponseGeneric.Error(
                    CodeApp.ErrService(
                        description.status.errorMessage ?: ""
                    )
                )

            } ?: ResponseGeneric.Error(CodeApp.ErrNotContainData)
        }
    }

    private fun setModelEntity(datum: Datum, path: String, modelDto: CryptoDto): CryptoEntity =
        CryptoEntity(
            id = modelDto.id,
            name = modelDto.name,
            symbol = modelDto.symbol,
            description = datum.description,
            dateAddCoin = modelDto.dateAdded,
            supplyMax = modelDto.totalSupply?.toDouble() ?: 0.0,
            supplyCirculation = modelDto.circulatingSupply.toDouble(),
            isInfinite = modelDto.infiniteSupply,
            rankList = modelDto.cmcRank ?: -1,
            price = modelDto.quote?.usd?.price ?: 0.0,
            percent1H = modelDto.quote?.usd?.percentChange1H,
            percent24H = modelDto.quote?.usd?.percentChange24H,
            percent7D = modelDto.quote?.usd?.percentChange7D,
            percent30D = modelDto.quote?.usd?.percentChange30D,
            percent60D = modelDto.quote?.usd?.percentChange60D,
            percent90D = modelDto.quote?.usd?.percentChange90D,
            marketCap = modelDto.quote?.usd?.marketCap,
            dominance = modelDto.quote?.usd?.marketCapDominance,
            logoPath = path,
            webSite = datum.urls.website[0]
        )

    private suspend fun saveImageFromUrl(imageUrl: String, fileName: String): File? {
        return try {
            val input = withContext(Dispatchers.IO) {
                URL(imageUrl).openStream()
            }
            val file = File(context.filesDir, fileName)
            input.use { inputStream ->
                FileOutputStream(file).use { outputStream ->
                    inputStream.copyTo(outputStream)
                }
            }
            file
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}