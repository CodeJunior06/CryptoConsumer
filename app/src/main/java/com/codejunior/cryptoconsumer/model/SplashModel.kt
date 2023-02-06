package com.codejunior.cryptoconsumer.model

import android.content.Context
import android.graphics.Bitmap
import com.bumptech.glide.Glide
import com.codejunior.cryptoconsumer.extension.render
import com.codejunior.cryptoconsumer.model.implement.ApiHelperImpl
import com.codejunior.cryptoconsumer.network.retrofit.model.data.ResponseCrypto
import com.codejunior.cryptoconsumer.network.retrofit.model.information.ResponseDescription
import com.codejunior.cryptoconsumer.network.room.DataBaseRoom
import com.codejunior.cryptoconsumer.network.room.entities.CryptoEntity
import com.codejunior.cryptoconsumer.utils.Defines
import com.codejunior.cryptoconsumer.utils.ResponseSealed
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.StringBuilder
import javax.inject.Inject

class SplashModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val retrofitConsumer: ApiHelperImpl,
    private val dataBase: DataBaseRoom
) {

    private lateinit var msg: Array<ResponseSealed>

    private lateinit var model: ResponseCrypto
    private lateinit var modelDescription: ResponseDescription
    private val map = HashMap<String, String>()
    suspend fun invokeListCrypto(): Array<ResponseSealed> {

        kotlin.runCatching { retrofitConsumer.getListCryptoAPI() }
            .onSuccess {
                if (it.code() == 200) {
                    model = it.body()!!
                    msg = arrayOf(
                        ResponseSealed.ChangeMessageBackground(Defines.SECOND_DOWNLOAD),
                        ResponseSealed.SecondPetition
                    )
                    return@onSuccess
                }
            }
            .onFailure {
                msg = arrayOf(ResponseSealed.MessageDialog("ERROR DE CONEXION"))
                return@onFailure
            }
        return msg
    }

    suspend fun invokeDescriptionCrypto(): Array<ResponseSealed> {

        kotlin.runCatching {
            retrofitConsumer.getCryptoInformationAPI(getConditionConsumerDescription())
        }
            .onSuccess {
                if (it.code() == 200) {
                    modelDescription = it.body()!!
                    msg = arrayOf(
                        ResponseSealed.ChangeMessageBackground("DESCARGANDO IMAGENES"),
                        ResponseSealed.GetBase64
                    )
                }
            }
            .onFailure { }
        return msg
    }

    private fun getConditionConsumerDescription(): String {
        val condition = StringBuilder()
        for (model in model.data) {
            condition.append(model.id)
            condition.append(",")
        }
        return condition.substring(0, condition.length - 1).toString()

    }

    suspend fun saveDataRoom(): Array<ResponseSealed> {
        withContext(Dispatchers.IO) {
            kotlin.runCatching {
                for (crypto in model.data) {
                    val modelDescription = modelDescription.data[crypto.id.toString()]!!
                    val logoBase64 = map[crypto.id.toString()]!!
                    dataBase.getCryptoDao()
                        .insertCrypto(model.render(crypto, modelDescription, logoBase64))
                }
            }
                .onSuccess {
                    msg = arrayOf(
                        ResponseSealed.NavigationInitFragment,
                        ResponseSealed.GetAllDataRoom
                    )
                }
                .onFailure {
                    println(it.message)
                    println(it.printStackTrace())
                }
        }

        return msg
    }

    suspend fun getBase64(): Array<ResponseSealed> {
        withContext(Dispatchers.IO) {
            kotlin.runCatching {
                for (modelDescription in modelDescription.data) {
                    val rta: Bitmap = Glide.with(context)
                        .asBitmap()
                        .load(modelDescription.value.logo).submit().get()!!
                    Defines.encodeImageBitmap(rta)?.let { map.put(modelDescription.value.id, it) }
                }
            }
                .onSuccess {
                    msg = arrayOf(
                        ResponseSealed.ChangeMessageBackground("GUARDANDO DATOS EN LA BASE DE DATOS"),
                        ResponseSealed.SaveDataRoom
                    )
                }
                .onFailure { }
        }
        return msg
    }

    suspend fun getAllDataRoom(): Array<ResponseSealed> {
        withContext(Dispatchers.IO) {

            kotlin.runCatching {
                dataBase.getCryptoDao().getAllCrypto()
            }
                .onSuccess { value -> msg = arrayOf(ResponseSealed.FillRecycler(value)) }
                .onFailure { }
        }
        return msg
    }

    suspend fun isConnectionAndVerifiedRoom(isSplash:Boolean): Array<ResponseSealed> {
        if (Defines.isConnected(context)) {
            return arrayOf(ResponseSealed.FirstPetition)
        }
        withContext(Dispatchers.IO) {

            kotlin.runCatching {
              dataBase.getCryptoDao().existData() == 0
            }.onSuccess {
                value ->
                msg = if (value){
                    arrayOf(ResponseSealed.MessageDialog("SIN ACCESO A INTERNET"))
                }else{
                    if(isSplash){
                        arrayOf(ResponseSealed.ChangeMessageBackground("TIENES DATOS EN LA BASE DE DATOS ..."),ResponseSealed.NavigationInitFragment)
                    }else{
                        arrayOf(ResponseSealed.GetAllDataRoom)
                    }

                }

            }.onFailure {

                msg  = arrayOf(ResponseSealed.MessageDialog("Error Data Base"))
            }


        }
        return msg
    }
}