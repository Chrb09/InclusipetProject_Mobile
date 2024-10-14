package com.example.inclusipet

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.inclusipet.roomDB.Usuario
import com.example.inclusipet.ui.theme.InclusipetTheme
import com.example.inclusipet.ui.theme.buttonStyle
import com.example.inclusipet.ui.theme.inter
import com.example.inclusipet.ui.theme.titleStyle
import com.example.inclusipet.viewModel.InclusipetViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Perfil(navController: NavController, viewModel: InclusipetViewModel, modifier: Modifier = Modifier, index: Int, mainActivity: MainActivity) {
    var usuarioList by remember{
        mutableStateOf(listOf<Usuario>())
    }
    var usuario by remember{
        mutableStateOf(Usuario(
            email = "",
            senha = "",
            nome = "",
            datanasc = "",
            cpf = "",
            telefone = "",
            endereco = "",
            logado = false
        ))
    }
    LaunchedEffect(
        key1 = true
    ) {
        CoroutineScope(Main).launch {
            usuarioList = viewModel.verificarLogin()
            usuario = usuarioList[0]
        }
    }
    InclusipetTheme(darkTheme = false, dynamicColor = false) {
        val layoutDirection = LocalLayoutDirection.current

        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding(),
            topBar = {
                TopAppBar(
                    windowInsets = WindowInsets(10.dp, 60.dp, 10.dp, 5.dp),
                    title = {
                        Text(
                        text = "Perfil",
                            style = titleStyle
                        )
                    },
                    actions = {
                        Image(
                            painter = painterResource(R.drawable.inclusipet_header),
                            modifier = Modifier.size(70.dp).padding(end = 20.dp, bottom = 10.dp),
                            contentDescription = null,
                        )
                    },
                )
            },
            bottomBar = {
                TabView(navController, index)
            }
        ) {
            it.calculateTopPadding()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(30.dp,115.dp,30.dp, 40.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(0.dp,0.dp,0.dp, 115.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                ){
                    Column(
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                    ){
                        Text(
                            text = usuario.nome,
                            style = TextStyle(
                                color = colorResource(R.color.grey_400),
                                fontSize = 20.sp,
                                fontFamily = inter,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Text(
                            text = usuario.endereco,
                            style = TextStyle(
                                color = colorResource(R.color.grey_400),
                                fontSize = 18.sp,
                                fontFamily = inter,
                                fontWeight = FontWeight.Normal
                            )
                        )
                        Text(
                            text = usuario.telefone,
                            style = TextStyle(
                                color = colorResource(R.color.grey_400),
                                fontSize = 18.sp,
                                fontFamily = inter,
                                fontWeight = FontWeight.Normal
                            )
                        )
                        Button(
                            onClick = {
                                    usuario.logado = false
                                    viewModel.upsertUsuario(usuario)
                                    Toast.makeText(
                                        mainActivity,
                                        "Saindo de "+usuario.nome+"...",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    navController.navigate(Routes.index)
                            },
                            modifier = Modifier.height(height = 38.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorResource(R.color.red),
                            ),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(
                                text = "Sair",
                                style = buttonStyle
                            )
                        }
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(20.dp),
                    ){
                        Text(
                            text = "Adoções",
                            style = titleStyle
                        )
                        HorizontalDivider(
                            color = colorResource(R.color.purple_100),
                            thickness = 3.dp,
                        )
                    }
                    adoptCardProfile("Fonseca", "Um cachorro de olhos marrons claros, de porte médio, velho, de pelagem curta...", R.drawable.card_placeholder,navController)
                    adoptCardProfile("Afrodite", "Gata jovem de olhos azuis de pelagem dourada, nascida sem as pernas traseiras...", R.drawable.card_placeholder2,navController)
                }
            }
        }
    }
    }

@Composable
fun adoptCardProfile(nome: String, descricao: String, image: Int, navController: NavController){
    Column(
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        Image(painter = painterResource(image),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize().height(180.dp).clip(shape = RoundedCornerShape(15.dp))
        )
        Text(
            text = nome,
            fontSize = 24.sp,
            style = titleStyle
        )
        Text(
            text = descricao,
            style = TextStyle(
                color = colorResource(R.color.grey_400),
                fontSize = 14.sp,
                fontFamily = inter,
                fontWeight = FontWeight.Medium
                )
        )
        Row(
            modifier = Modifier.padding(0.dp, 10.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ){
            Button(
                onClick = {

                },
                modifier = Modifier.height(height = 38.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.red),
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Deletar",
                    style = buttonStyle
                )
            }
            Button(
                onClick = {

                },
                modifier = Modifier.height(38.dp).fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.white),
                    contentColor = colorResource(R.color.grey_100)
                ),
                border = BorderStroke(2.dp, colorResource(R.color.grey_100))
            ) {
                Text(
                    text = "Marcar como Adotado",
                    style = buttonStyle
                )
            }
        }
    }

}
