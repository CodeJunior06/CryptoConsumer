package com.codejunior.cryptoconsumer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codejunior.cryptoconsumer.model.SplashModel
import com.codejunior.cryptoconsumer.utils.ResponseSealed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val splashModel:SplashModel): ViewModel() {

    private val _message = MutableStateFlow("")
    val messageState: StateFlow<String> = _message

    fun invoke(){
        viewModelScope.launch {
            when (val responseMessage = splashModel.invokeListCrypto()) {
              is ResponseSealed.Message -> _message.emit(responseMessage.message)
          }
        }
    }
}