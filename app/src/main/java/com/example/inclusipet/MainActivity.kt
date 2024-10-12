package com.example.inclusipet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = Routes.index) {
                composable(Routes.index){
                    Index(navController)
                }
                composable(Routes.login){
                    Login(navController)
                }
                composable(Routes.cadastro){
                    Cadastro(navController)
                }
                composable(Routes.adote){
                    Adote(navController, index = 0)
                }
                composable(Routes.informacoes){
                    Informacoes(navController, index = 0)
                }
                composable(Routes.anuncio){
                    Anuncio(navController, index = 1)
                }
                composable(Routes.perfil){
                    Perfil(navController, index = 2)
                }
            }
        }
    }
}

