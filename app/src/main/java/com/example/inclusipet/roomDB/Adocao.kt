package com.example.inclusipet.roomDB

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation


@Entity
data class Adocao (
    val nome: String,
    val idade: Int,
    val especie: String,
    val porte: String,
    val sexo: String,
    val castrado: Boolean,
    val descricao: String,
    val idCliente: Int,

    @PrimaryKey(autoGenerate = true)
    val idAdocao: Int = 0
)