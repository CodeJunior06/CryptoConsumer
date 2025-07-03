package com.codejunior.cryptoconsumer.model

import android.content.Context
import com.codejunior.cryptoconsumer.domain.model.Crypto
import com.codejunior.cryptoconsumer.model.implement.ApiDataBaseImpl
import com.codejunior.cryptoconsumer.model.implement.ApiServiceImpl
import com.codejunior.cryptoconsumer.network.ResponseGeneric
import com.codejunior.cryptoconsumer.utils.Utils.Companion.isConnected
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SplashModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val serviceImpl: ApiServiceImpl,
    private val roomImpl: ApiDataBaseImpl,
) {
    suspend fun getAllDataRoom() {
        withContext(Dispatchers.IO) {

            kotlin.runCatching {
                roomImpl.fetchCrypto()
            }
                .onSuccess { }
                .onFailure { }
        }
    }

    suspend fun isConnectionAndVerifiedRoom(): ResponseGeneric<List<Crypto>> {
        return withContext(Dispatchers.IO) {
            when (context.isConnected()) {
                true -> serviceImpl.fetchCrypto()
                else -> roomImpl.fetchCrypto()
            }
        }
    }
}