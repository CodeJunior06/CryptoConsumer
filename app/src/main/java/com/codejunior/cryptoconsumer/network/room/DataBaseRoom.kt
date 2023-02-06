package com.codejunior.cryptoconsumer.network.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.codejunior.cryptoconsumer.network.room.dao.CryptoDao
import com.codejunior.cryptoconsumer.network.room.entities.CryptoEntity

@Database([CryptoEntity::class], version = 1, exportSchema = false)
abstract class DataBaseRoom : RoomDatabase() {

    abstract fun getCryptoDao(): CryptoDao

}