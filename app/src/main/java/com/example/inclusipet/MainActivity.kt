package com.example.inclusipet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.inclusipet.roomDB.InclusipetDataBase
import com.example.inclusipet.roomDB.Usuario
import com.example.inclusipet.viewModel.InclusipetViewModel
import com.example.inclusipet.viewModel.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            InclusipetDataBase::class.java,
            "inclusipet.db"
        ).build()
    }

    private val viewModelInclusipet by viewModels<InclusipetViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return InclusipetViewModel(Repository(db)) as T
                }
            }
        }
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            var usuarioList by remember{
                mutableStateOf(listOf<Usuario>())
            }
            var paginaInicial by remember{
                mutableStateOf(Routes.loading)
            }
            LaunchedEffect(
                key1 = true
            ) {
                CoroutineScope(Main).launch {
                    usuarioList = viewModelInclusipet.verificarLogin()
                    if(usuarioList.isNotEmpty()){
                        paginaInicial = Routes.adote
                    }else{
                        paginaInicial = Routes.index
                    }
                }
            }

            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = paginaInicial) {
                composable(Routes.index){
                    Index(navController, viewModelInclusipet)
                }
                composable(Routes.login){
                    Login(navController, viewModelInclusipet, mainActivity = this@MainActivity)
                }
                composable(Routes.cadastro){
                    Cadastro(navController, viewModelInclusipet, mainActivity = this@MainActivity)
                }
                composable(Routes.adote){
                    Adote(navController, index = 0, viewModel = viewModelInclusipet, mainActivity = this@MainActivity)
                }
                composable(Routes.informacoes){
                    Informacoes(navController, index = 0, viewModel = viewModelInclusipet, mainActivity = this@MainActivity)
                }
                composable(Routes.anuncio){
                    Anuncio(navController, index = 1, viewModel = viewModelInclusipet, mainActivity = this@MainActivity)
                }
                composable(Routes.perfil){
                    Perfil(navController, index = 2, viewModel = viewModelInclusipet, mainActivity = this@MainActivity)
                }
                composable(Routes.loading){
                    Loading(navController, viewModelInclusipet)
                }
            }

        }
    }
}

