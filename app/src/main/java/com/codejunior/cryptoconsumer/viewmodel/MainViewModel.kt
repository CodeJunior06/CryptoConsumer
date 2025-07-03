package com.codejunior.cryptoconsumer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codejunior.cryptoconsumer.model.SplashModel
import com.codejunior.cryptoconsumer.network.room.entities.CryptoEntity
import com.codejunior.cryptoconsumer.utils.ResponseSealed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val baseModel:SplashModel) :ViewModel(){

    private val _lstCrypto = MutableStateFlow<List<CryptoEntity>>(emptyList())
    val lstCrypto: StateFlow<List<CryptoEntity>?> = _lstCrypto


    fun invoke() {
        viewModelScope.launch {

        }
    }
}