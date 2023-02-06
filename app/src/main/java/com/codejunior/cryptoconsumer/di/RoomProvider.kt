package com.codejunior.cryptoconsumer.di

import android.content.Context
import androidx.room.Room
import com.codejunior.cryptoconsumer.network.Constants
import com.codejunior.cryptoconsumer.network.room.DataBaseRoom
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomProvider {

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        DataBaseRoom::class.java,
        Constants.NAME_DATA_BASE
    ).build()

    @Singleton
    @Provides
    fun provideCharacterDAO(dataBase: DataBaseRoom) = dataBase.getCryptoDao()

}