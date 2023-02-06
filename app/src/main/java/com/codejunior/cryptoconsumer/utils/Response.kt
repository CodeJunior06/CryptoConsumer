package com.codejunior.cryptoconsumer.utils

import com.codejunior.cryptoconsumer.network.room.entities.CryptoEntity

sealed class ResponseSealed(){

    data class MessageDialog(val message:String):ResponseSealed()
    data class ChangeMessageBackground(val message:String):ResponseSealed()
    object FirstPetition:ResponseSealed()
    object SecondPetition:ResponseSealed()

    object GetBase64:ResponseSealed()
    object SaveDataRoom:ResponseSealed()
    object NavigationInitFragment:ResponseSealed()
    object GetAllDataRoom:ResponseSealed()
    data class FillRecycler(val lst:List<CryptoEntity>) : ResponseSealed()


}


