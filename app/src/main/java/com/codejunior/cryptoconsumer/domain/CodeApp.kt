package com.codejunior.cryptoconsumer.domain

sealed class CodeApp {
    //Err
    data object ErrNotContainData :CodeApp()
    data object ErrNotInternet : CodeApp()
    data object ErrDataBase : CodeApp()
    data class ErrService(val err:String) : CodeApp()

    //Success
    data object FetchCrypto : CodeApp()
}