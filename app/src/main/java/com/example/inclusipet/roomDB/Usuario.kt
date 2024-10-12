package com.example.inclusipet.roomDB


import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Usuario (
    val email: String,
    val senha: String,
    val nome: String,
    val datanasc: String,
    val cpf: String,
    val telefone: String,
    val endereco: String,

    @PrimaryKey(autoGenerate = true)
    val idCliente: Int = 0
)