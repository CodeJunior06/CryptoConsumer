package com.codejunior.cryptoconsumer.domain

sealed class StateGeneric<out T> {
    data object Load : StateGeneric<Nothing>()
    data class Dialog(val isErr:Boolean = true, val message: CodeApp) : StateGeneric<Nothing>()
    data class Success<T>(val suc: T) : StateGeneric<Nothing>()
    data class Navigate(val nav: Int) : StateGeneric<Nothing>()
}