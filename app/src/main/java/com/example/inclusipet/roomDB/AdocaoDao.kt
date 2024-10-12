package com.example.inclusipet.roomDB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface AdocaoDao {
    @Upsert
    suspend fun upsertAdocao(adocao: Adocao)

    @Delete
    suspend fun deleteAdocao(adocao: Adocao)

    @Query("SELECT * FROM adocao WHERE idCliente = :idCliente")
    fun getAdocaoUsuario(idCliente: Int): Flow<List<Adocao>>

    @Query("SELECT * FROM adocao")
    fun getAllAdocao(): Flow<List<Adocao>>

}