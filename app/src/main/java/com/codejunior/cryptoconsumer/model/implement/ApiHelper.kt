package com.codejunior.cryptoconsumer.model.implement

import com.codejunior.cryptoconsumer.domain.model.Crypto
import com.codejunior.cryptoconsumer.network.ResponseGeneric

interface ApiHelper{
    suspend fun fetchCrypto() : ResponseGeneric<List<Crypto>>
}