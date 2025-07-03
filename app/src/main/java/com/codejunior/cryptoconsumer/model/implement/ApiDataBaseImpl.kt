package com.codejunior.cryptoconsumer.model.implement

import com.codejunior.cryptoconsumer.domain.CodeApp
import com.codejunior.cryptoconsumer.domain.fromEntityToDomain
import com.codejunior.cryptoconsumer.domain.model.Crypto
import com.codejunior.cryptoconsumer.network.ResponseGeneric
import com.codejunior.cryptoconsumer.network.room.dao.CryptoDao
import javax.inject.Inject

class ApiDataBaseImpl @Inject constructor(private val room: CryptoDao) : ApiHelper {
    override suspend fun fetchCrypto(): ResponseGeneric<List<Crypto>> {
        return runCatching {
            room.getAllCrypto().fromEntityToDomain()
        }.fold(
            onSuccess = { ResponseGeneric.Success(it) },
            onFailure = { ResponseGeneric.Error(CodeApp.ErrDataBase) }
        )
    }
}