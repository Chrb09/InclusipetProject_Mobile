package com.example.inclusipet.roomDB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface UsuarioDao{

    @Upsert
    suspend fun upsertUsuario(usuario: Usuario)

    @Delete
    suspend fun deleteUsuario(usuario: Usuario)

    @Query("SELECT * FROM usuario")
    fun getAllUsuario(): Flow<List<Usuario>>

    @Query("SELECT * FROM usuario WHERE email = :email")
    suspend fun verificarEmail(email: String): List<Usuario>

    @Query("SELECT * FROM usuario WHERE logado = true")
    suspend fun verificarLogin(): List<Usuario>

    @Query("SELECT * FROM usuario WHERE email = :email AND senha = :senha")
    suspend fun loginUsuario(email: String, senha: String): List<Usuario>


}