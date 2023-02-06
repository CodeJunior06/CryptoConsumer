package com.codejunior.cryptoconsumer.network.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.codejunior.cryptoconsumer.network.room.entities.CryptoEntity

@Dao
interface CryptoDao {

    @Insert(entity = CryptoEntity::class, onConflict = REPLACE)
    suspend fun insertCrypto(vararg model: CryptoEntity)

    @Query("SELECT COUNT(*) FROM crypto")
    suspend fun existData(): Int

    @Query("DELETE FROM crypto")
    suspend fun deleteAll()

    @Query("SELECT * FROM crypto")
    suspend fun getAllCrypto() : List<CryptoEntity>

}