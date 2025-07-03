package com.codejunior.cryptoconsumer.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codejunior.cryptoconsumer.domain.StateGeneric
import com.codejunior.cryptoconsumer.model.SplashModel
import com.codejunior.cryptoconsumer.network.ResponseGeneric
import com.codejunior.cryptoconsumer.utils.Utils
import com.codejunior.cryptoconsumer.utils.ResponseSealed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val splashModel: SplashModel) : ViewModel() {

    private val _messageSuccess = MutableStateFlow(Utils.INIT_DOWNLOAD)

    private val _navigation = MutableStateFlow(false)

    private val _stateSplash = MutableStateFlow<StateGeneric<*>>(StateGeneric.Load)
    val stateSplash: StateFlow<StateGeneric<*>> = _stateSplash

    operator fun invoke() {
        viewModelScope.launch {
            when (val res = splashModel.isConnectionAndVerifiedRoom()) {

                is ResponseGeneric.Success -> {
                    //_stateSplash.emit(StateGeneric.Success(true))
                    Log.i(javaClass.simpleName, "Response Finish ${res.data}")
                }

                is ResponseGeneric.Error -> {
                    _stateSplash.emit(StateGeneric.Dialog(message = res.err))
                }
            }
        }
    }

    fun navigateInitFragment() { _stateSplash.value = StateGeneric.Navigate(0) }
}