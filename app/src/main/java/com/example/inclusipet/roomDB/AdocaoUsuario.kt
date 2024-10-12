package com.example.inclusipet.roomDB

import androidx.room.Embedded
import androidx.room.Relation

data class AdocaoUsuario(
    @Embedded val usuario: Usuario,
    @Relation(
        parentColumn = "idCliente",
        entityColumn = "idCliente"
    )
    val adocao: Adocao
)