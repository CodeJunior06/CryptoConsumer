package com.codejunior.cryptoconsumer.network

import com.codejunior.cryptoconsumer.domain.CodeApp

sealed class ResponseGeneric<out T> {

    data class Success<T>(val data:T) : ResponseGeneric<T>()
    data class Error(val err: CodeApp) : ResponseGeneric<Nothing>()
}