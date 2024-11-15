package com.example.inclusipet.roomDB

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        Adocao::class,
        Usuario::class
               ],
    version = 1,
)

abstract class InclusipetDataBase: RoomDatabase() {
    abstract fun adocaoDao(): AdocaoDao
    abstract fun usuarioDao(): UsuarioDao
}