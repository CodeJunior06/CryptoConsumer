package com.codejunior.cryptoconsumer.utils

import android.content.Context
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Base64
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.codejunior.cryptoconsumer.R
import java.io.ByteArrayOutputStream

class Defines {

    companion object {

        const val INIT_DOWNLOAD: String = "DESCARGANDO LISTA DE CRYPTOS ..."
        const val SECOND_DOWNLOAD: String = "DESCARGANDO DESCIPCION DE CADA CRYPTO"


        fun encodeImageBitmap(bm: Bitmap): String? {
            val baos = ByteArrayOutputStream()
            bm.compress(Bitmap.CompressFormat.PNG, 100, baos)
            val b = baos.toByteArray()
            return Base64.encodeToString(b, Base64.DEFAULT)
        }

        @RequiresApi(Build.VERSION_CODES.M)
        fun isConnected(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
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
    }
}