package com.example.inclusipet.viewModel

import com.example.inclusipet.roomDB.Adocao
import com.example.inclusipet.roomDB.InclusipetDataBase
import com.example.inclusipet.roomDB.Usuario
import kotlinx.coroutines.flow.Flow

class Repository(private val db: InclusipetDataBase) {
    suspend fun upsertAdocao(adocao: Adocao) {
        db.adocaoDao().upsertAdocao(adocao)
    }

    suspend fun deleteAdocao(adocao: Adocao) {
        db.adocaoDao().deleteAdocao(adocao)
    }

    fun getAdocaoUsuario(idCliente: Int) = db.adocaoDao().getAdocaoUsuario(idCliente)

    fun getAllAdocao() = db.adocaoDao().getAllAdocao()

    suspend fun upsertUsuario(usuario: Usuario) {
        db.usuarioDao().upsertUsuario(usuario)
    }

    suspend fun deleteUsuario(usuario: Usuario) {
        db.usuarioDao().deleteUsuario(usuario)
    }

    fun getAllUsuario() = db.usuarioDao().getAllUsuario()

    fun loginUsuario(email: String, senha: String) = db.usuarioDao().loginUsuario(email, senha)
}