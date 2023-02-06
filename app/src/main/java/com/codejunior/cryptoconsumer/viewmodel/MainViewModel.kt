package com.codejunior.cryptoconsumer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codejunior.cryptoconsumer.model.SplashModel
import com.codejunior.cryptoconsumer.network.room.entities.CryptoEntity
import com.codejunior.cryptoconsumer.utils.ResponseSealed
import dagger.hilt.InstallIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val baseModel:SplashModel) :ViewModel(){


    private val _messageDialogNotInternet = MutableStateFlow("")
    val messageStateDialog: StateFlow<String> = _messageDialogNotInternet

    private val _lstCrypto = MutableStateFlow<List<CryptoEntity>>(emptyList())
    val lstCrypto: StateFlow<List<CryptoEntity>?> = _lstCrypto


    fun invoke() {
        viewModelScope.launch {
            recursive(*baseModel.isConnectionAndVerifiedRoom(false))
        }
    }

    private suspend fun recursive(vararg responseSealedVarArgs: ResponseSealed) {

        for (responseSealed in responseSealedVarArgs) {
            when (responseSealed) {
                is ResponseSealed.MessageDialog -> {
                    _messageDialogNotInternet.emit(responseSealed.message)
                }
                ResponseSealed.FirstPetition -> recursive(*baseModel.invokeListCrypto())
                ResponseSealed.SecondPetition -> recursive(*baseModel.invokeDescriptionCrypto())
                ResponseSealed.GetBase64 -> recursive(*baseModel.getBase64())
                ResponseSealed.SaveDataRoom -> recursive(*baseModel.saveDataRoom())
                ResponseSealed.GetAllDataRoom -> recursive(*baseModel.getAllDataRoom())
                is ResponseSealed.FillRecycler -> _lstCrypto.emit(responseSealed.lst)
                else -> println(responseSealed.toString())
            }
        }
    }



}