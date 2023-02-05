package com.codejunior.cryptoconsumer.utils

sealed class ResponseSealed(val message:String){

    data class Message(private val msg:String):ResponseSealed(msg)
}


