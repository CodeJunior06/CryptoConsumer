package com.codejunior.cryptoconsumer.utils

import android.content.Context
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Base64
import android.util.Log
import androidx.core.content.ContextCompat
import com.codejunior.cryptoconsumer.R
import java.io.ByteArrayOutputStream

class Utils {

    companion object {

        const val INIT_DOWNLOAD: String = "DESCARGANDO LISTA DE CRYPTOS ..."
        const val SECOND_DOWNLOAD: String = "DESCARGANDO DESCIPCION DE CADA CRYPTO"


        fun encodeImageBitmap(bm: Bitmap): String? {
            val baos = ByteArrayOutputStream()
            bm.compress(Bitmap.CompressFormat.PNG, 100, baos)
            val b = baos.toByteArray()
            return Base64.encodeToString(b, Base64.DEFAULT)
        }

        fun Context.isConnected(): Boolean {
            val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            capabilities?.let {
                return when{
                    it.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    it.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    it.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    else -> false
                }
            }
            return false
        }

        fun getColor(number:String,context: Context) :Int {

            val numero = number.toDouble()

            if(numero<0){
                return ContextCompat.getColor(context, R.color.red)
            }
            return ContextCompat.getColor(context, R.color.green)
        }

        fun toConverter(price:String) :String{
           val lst = price.split(".")
            return lst[0]+"."+lst[1].substring(0,5)
        }

    }
}