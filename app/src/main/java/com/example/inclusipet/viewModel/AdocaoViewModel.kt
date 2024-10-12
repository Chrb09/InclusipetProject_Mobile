package com.example.inclusipet.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inclusipet.roomDB.Adocao
import kotlinx.coroutines.launch

class AdocaoViewModel(private val repository: Repository): ViewModel()  {
    fun getAdocaoUsuario() = repository.getAdocaoUsuario()
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
}