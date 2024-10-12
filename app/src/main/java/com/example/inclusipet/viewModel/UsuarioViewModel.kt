package com.example.inclusipet.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.inclusipet.roomDB.Usuario
import kotlinx.coroutines.launch

class UsuarioViewModel(private val repository: Repository): ViewModel() {
    fun getUsuario() = repository.getAllUsuario().asLiveData(viewModelScope.coroutineContext)

    fun upsertPessoa(usuario: Usuario) {
        viewModelScope.launch{
            repository.upsertUsuario(usuario)
        }
    }

    fun deletePessoa(usuario: Usuario) {
        viewModelScope.launch{
            repository.deleteUsuario(usuario)
        }
    }
}