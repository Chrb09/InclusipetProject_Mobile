package com.example.inclusipet.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.inclusipet.roomDB.Adocao
import com.example.inclusipet.roomDB.Usuario
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class InclusipetViewModel(private val repository: Repository): ViewModel()  {
    private var selectedAdocao = 0

    fun updateSelectedAdocao(adocao: Int) {
        this.selectedAdocao = adocao
    }

    fun getSelectedAdocao(): Int {
        return this.selectedAdocao
    }

    fun getAdocaoUsuario(idCliente: Int) = repository.getAdocaoUsuario(idCliente).asLiveData(viewModelScope.coroutineContext)
    fun getAllAdocao() = repository.getAllAdocao().asLiveData(viewModelScope.coroutineContext)
    fun getInfoAdocao(idCliente: Int, idAdocao: Int) = repository.getInfoAdocao(idCliente, idAdocao)
    suspend fun getAdocaoCod(idAdocao: Int): List<Adocao> {
        val deferred: Deferred<List<Adocao>> = viewModelScope.async {
            repository.getAdocaoCod(idAdocao)
        }
        return deferred.await()
    }

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
    suspend fun getIdUsuario(idCliente: Int): List<Usuario> {
        val deferred: Deferred<List<Usuario>> = viewModelScope.async {
            repository.getIdUsuario(idCliente)
        }
        return deferred.await()
    }
}