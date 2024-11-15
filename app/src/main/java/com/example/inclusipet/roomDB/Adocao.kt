package com.example.inclusipet.roomDB

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Adocao (
    val nome: String,
    var idade: Int,
    val especie: String,
    val porte: String,
    val sexo: String,
    var castrado: Boolean,
    var endereco: String,
    val descricao: String,
    var imagemURL: String,
    var adotado: Boolean,
    val idCliente: Int,

    @PrimaryKey(autoGenerate = true)
    val idAdocao: Int = 0
)