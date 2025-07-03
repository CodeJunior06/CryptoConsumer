package com.codejunior.cryptoconsumer.di

import android.content.Context
import androidx.room.RoomDatabase
import com.codejunior.cryptoconsumer.model.implement.ApiHelper
import com.codejunior.cryptoconsumer.model.implement.ApiServiceImpl
import com.codejunior.cryptoconsumer.network.retrofit.ApiService
import com.codejunior.cryptoconsumer.network.Constants
import com.codejunior.cryptoconsumer.network.room.DataBaseRoom
import com.codejunior.cryptoconsumer.network.room.dao.CryptoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RetrofitProvider {

    @Provides
    @Singleton
    fun provideBaseUrl() :String = Constants.BASE_URL

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {


        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor { chain ->
            val original: Request = chain.request()

            // Request customization: add request headers
            val requestBuilder: Request.Builder = original.newBuilder()
                .header("X-CMC_PRO_API_KEY", Constants.API_KEY)
            val request: Request = requestBuilder.build()
            chain.proceed(request)
        }

        httpClient.connectTimeout(30, TimeUnit.SECONDS)
        httpClient.readTimeout(30, TimeUnit.SECONDS)
        httpClient.addNetworkInterceptor(logging)

       return httpClient.build()

    }


    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, url:String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(url)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(@ApplicationContext context: Context,service: ApiService, dao: CryptoDao) : ApiHelper = ApiServiceImpl(context,service, dao)

}