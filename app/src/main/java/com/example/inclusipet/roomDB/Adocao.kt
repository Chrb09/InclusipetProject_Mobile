package com.example.inclusipet.roomDB

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Adocao (
    val nome: String,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
