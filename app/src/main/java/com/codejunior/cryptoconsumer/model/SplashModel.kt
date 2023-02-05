package com.codejunior.cryptoconsumer.model

import com.codejunior.cryptoconsumer.model.implement.ApiHelperImpl
import com.codejunior.cryptoconsumer.utils.ResponseSealed
import kotlinx.serialization.builtins.serializer
import okhttp3.internal.wait
import javax.inject.Inject

class SplashModel @Inject constructor(private val retrofitConsumer: ApiHelperImpl) {

    suspend fun invokeListCrypto(): ResponseSealed {
        lateinit var  msg: ResponseSealed
        kotlin.runCatching { retrofitConsumer.getListCryptoAPI() }
            .onSuccess {
                if (it.code() == 200) {
                    msg = ResponseSealed.Message("success")
                    return@onSuccess
                }
            }
            .onFailure {
                msg = ResponseSealed.Message("failure")
                return@onFailure
            }
        return msg
    }
}