package com.codejunior.cryptoconsumer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codejunior.cryptoconsumer.model.SplashModel
import com.codejunior.cryptoconsumer.utils.Defines
import com.codejunior.cryptoconsumer.utils.ResponseSealed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val splashModel: SplashModel) : ViewModel() {

    private val _messageDialogNotInternet = MutableStateFlow("")
    val messageStateDialog: StateFlow<String> = _messageDialogNotInternet

    private val _messageSuccess = MutableStateFlow(Defines.INIT_DOWNLOAD)
    val messageStateSuccess: StateFlow<String> = _messageSuccess

    private val _navigation = MutableStateFlow(false)
    val navigation: StateFlow<Boolean> = _navigation


    fun invoke() {

        viewModelScope.launch(Dispatchers.Default) {
            val responseThread = withContext(viewModelScope.coroutineContext) {
                recursive(*splashModel.isConnectionAndVerifiedRoom(true))
            }

            if(responseThread is ResponseSealed.MessageDialog){
                _messageDialogNotInternet.emit("OKOKOKOK")
                return@launch
            }

        }

    }

    private suspend fun recursive(vararg responseSealedVarArgs: ResponseSealed): ResponseSealed {
        for (responseSealed in responseSealedVarArgs) {

            when (responseSealed) {
                is ResponseSealed.MessageDialog -> {
                    return  responseSealed
                }
                is ResponseSealed.ChangeMessageBackground -> {
                    _messageSuccess.emit(responseSealed.message)
                }
                ResponseSealed.FirstPetition -> recursive(*splashModel.invokeListCrypto())
                ResponseSealed.SecondPetition -> recursive(*splashModel.invokeDescriptionCrypto())
                ResponseSealed.GetBase64 -> recursive(*splashModel.getBase64())
                ResponseSealed.SaveDataRoom -> recursive(*splashModel.saveDataRoom())
                ResponseSealed.NavigationInitFragment -> {
                    delay(1500)
                    _navigation.emit(true)
                }
                else -> println("NOS SALIMOS")
            }
        }
        return responseSealedVarArgs[0]
    }
}