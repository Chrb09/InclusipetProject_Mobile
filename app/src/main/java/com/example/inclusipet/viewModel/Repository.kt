package com.example.inclusipet.viewModel

import com.example.inclusipet.roomDB.Adocao
import com.example.inclusipet.roomDB.InclusipetDataBase
import com.example.inclusipet.roomDB.Usuario
import com.example.inclusipet.roomDB.UsuarioDao
import kotlinx.coroutines.flow.Flow

class Repository(private val db: InclusipetDataBase) {
    suspend fun upsertAdocao(adocao: Adocao) {
        db.adocaoDao().upsertAdocao(adocao)
    }

    suspend fun deleteAdocao(adocao: Adocao) {
        db.adocaoDao().deleteAdocao(adocao)
    }
    fun getInfoAdocao(idCliente: Int, idAdocao: Int) = db.adocaoDao().getInfoAdocao(idCliente, idAdocao)

    fun getAdocaoUsuario(idCliente: Int) = db.adocaoDao().getAdocaoUsuario(idCliente)

    fun getAllAdocao() = db.adocaoDao().getAllAdocao()

    fun getAdocaoCod(idAdocao: Int) = db.adocaoDao().getAdocaoCod(idAdocao)

    suspend fun upsertUsuario(usuario: Usuario) {
        db.usuarioDao().upsertUsuario(usuario)
    }

    suspend fun deleteUsuario(usuario: Usuario) {
        db.usuarioDao().deleteUsuario(usuario)
    }

    fun getAllUsuario() = db.usuarioDao().getAllUsuario()

    suspend fun loginUsuario(email: String, senha: String): List<Usuario> {
        return db.usuarioDao().loginUsuario(email, senha)
    }

    suspend fun verificarEmail(email: String): List<Usuario>{
        return db.usuarioDao().verificarEmail(email)
    }

    suspend fun verificarLogin(): List<Usuario>{
        return db.usuarioDao().verificarLogin()
    }

}