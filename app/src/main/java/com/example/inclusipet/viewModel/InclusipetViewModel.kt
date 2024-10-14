package com.example.inclusipet.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.inclusipet.roomDB.Adocao
import com.example.inclusipet.roomDB.Usuario
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class InclusipetViewModel(private val repository: Repository): ViewModel()  {
    fun getAdocaoUsuario(idCliente: Int) = repository.getAdocaoUsuario(idCliente)
    fun getAllAdocao() = repository.getAllAdocao()

    fun upsertAdocao(adocao: Adocao) {
        viewModelScope.launch{
            repository.upsertAdocao(adocao)
        }
    }

    fun deleteAdocao(adocao: Adocao) {
        viewModelScope.launch{
            repository.deleteAdocao(adocao)
        }
    }

    fun getUsuario() = repository.getAllUsuario().asLiveData(viewModelScope.coroutineContext)

    fun upsertUsuario(usuario: Usuario) {
        viewModelScope.launch{
            repository.upsertUsuario(usuario)
        }
    }

    fun deleteUsuario(usuario: Usuario) {
        viewModelScope.launch{
            repository.deleteUsuario(usuario)
        }
    }

    suspend fun loginUsuario(email: String, senha: String): List<Usuario> {
        val deferred: Deferred<List<Usuario>> = viewModelScope.async {
            repository.loginUsuario(email, senha)
        }
        return deferred.await()
    }

    suspend fun verificarEmail(email: String):List<Usuario>{
        val deferred: Deferred<List<Usuario>> = viewModelScope.async {
            repository.verificarEmail(email)
        }
        return deferred.await()

    }
    suspend fun verificarLogin():List<Usuario> {
        val deferred: Deferred<List<Usuario>> = viewModelScope.async {
            repository.verificarLogin()
        }
        return deferred.await()
    }
}