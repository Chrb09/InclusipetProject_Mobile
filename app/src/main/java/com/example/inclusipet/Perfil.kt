package com.example.inclusipet

import android.net.Uri
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.inclusipet.roomDB.Adocao
import com.example.inclusipet.roomDB.Usuario
import com.example.inclusipet.ui.theme.InclusipetTheme
import com.example.inclusipet.ui.theme.buttonStyle
import com.example.inclusipet.ui.theme.inter
import com.example.inclusipet.ui.theme.labelStyle
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
    val shouldShowDialog = remember { mutableStateOf(false) } // 1
    LaunchedEffect(
        key1 = true
    ) {
        CoroutineScope(Main).launch {
            usuarioList = viewModel.verificarLogin()
            usuario = usuarioList[0]
        }
    }

    var adocaoList by remember{
        mutableStateOf(listOf<Adocao>())
    }

    viewModel.getAdocaoUsuario(usuario.idCliente).observe(mainActivity) {
        adocaoList = it
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
                    .padding(30.dp,115.dp,30.dp, 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp,0.dp,0.dp, 85.dp),
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
                    if (adocaoList.isEmpty()){
                        Text(
                            text = "Nenhuma adoção cadastrada!",
                            style = labelStyle
                        )

                    }else{
                    LazyColumn {
                        items(adocaoList) {adocao ->
                            adoptCardProfile(adocao,navController, viewModel, mainActivity, shouldShowDialog)
                            }
                        }
                    }
                }
            }
        }
    }
    }

@Composable
fun adoptCardProfile(adocao: Adocao, navController: NavController, viewModel: InclusipetViewModel, mainActivity: MainActivity, shouldShowDialog: MutableState<Boolean>){
    if (shouldShowDialog.value) {
        MyAlertDialog(shouldShowDialog = shouldShowDialog, adocao, viewModel, mainActivity)
    }
    Column(
        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 20.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        Image(
            if(adocao.imagemURL == "") painterResource(R.drawable.placeholder) else rememberAsyncImagePainter(adocao.imagemURL),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize().height(180.dp).clip(shape = RoundedCornerShape(15.dp))
        )
        Text(
            text = adocao.nome,
            fontSize = 24.sp,
            style = titleStyle
        )
        Text(
            text = adocao.descricao,
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
                    shouldShowDialog.value = true
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
            if(!adocao.adotado) {
                Button(
                    onClick = {
                        adocao.adotado = true
                        viewModel.upsertAdocao(adocao)
                        Toast.makeText(
                            mainActivity,
                            "${adocao.nome} foi adotado!",
                            Toast.LENGTH_SHORT
                        ).show()
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
            }else{
                Button(
                    onClick = {
                        adocao.adotado = false
                        viewModel.upsertAdocao(adocao)
                        Toast.makeText(
                            mainActivity,
                            "${adocao.nome} não está mais adotado",
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    modifier = Modifier.height(38.dp).fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.green_100),
                        contentColor = colorResource(R.color.white)
                    ),
                    border = BorderStroke(2.dp, colorResource(R.color.green_100))
                ) {
                    Text(
                        text = "Adotado",
                        style = buttonStyle
                    )
                }
            }
        }
    }

}

@Composable
fun MyAlertDialog(shouldShowDialog: MutableState<Boolean>, adocao: Adocao, viewModel: InclusipetViewModel, mainActivity: MainActivity) {
    if (shouldShowDialog.value) { // 2
        AlertDialog( // 3
            onDismissRequest = { // 4
                shouldShowDialog.value = false
            },
            containerColor = Color.White,
            // 5
            title = { Text(text = "Confirmar deleção") },
            text = { Text(text = "Voce irá deletar ${adocao.nome}") },

            dismissButton = {
                Button(
                    onClick = {
                        shouldShowDialog.value = false
                    },
                    modifier = Modifier,
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = colorResource(R.color.grey_100)
                    ),
                    border = BorderStroke(2.dp, colorResource(R.color.grey_100))
                ) {
                    Text(
                        text = "Cancelar"
                    )
                }
            },


            confirmButton = { // 6
                Button(
                    onClick = {
                        shouldShowDialog.value = false
                        Toast.makeText(
                            mainActivity,
                            "${adocao.nome} foi deletado!",
                            Toast.LENGTH_SHORT
                        ).show()
                        viewModel.deleteAdocao(adocao)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.red),
                    ),
                    modifier = Modifier,
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "Deletar",
                    )
                }
            }
        )
    }
}