package com.example.inclusipet

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.inclusipet.roomDB.Adocao
import com.example.inclusipet.roomDB.Usuario
import com.example.inclusipet.ui.theme.InclusipetTheme
import com.example.inclusipet.ui.theme.Purple100
import com.example.inclusipet.ui.theme.buttonStyle
import com.example.inclusipet.ui.theme.labelStyle
import com.example.inclusipet.ui.theme.titleStyle
import com.example.inclusipet.viewModel.InclusipetViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Anuncio(navController: NavController, viewModel: InclusipetViewModel, modifier: Modifier = Modifier, index: Int, mainActivity: MainActivity) {
    InclusipetTheme(darkTheme = false, dynamicColor = false) {
        val layoutDirection = LocalLayoutDirection.current
        var nome by remember{
            mutableStateOf("")
        }
        var idade by remember{
            mutableStateOf("")
        }
        var imagem by remember{
            mutableStateOf("")
        }
        var especie by remember{
            mutableStateOf("Cachorro")
        }
        var porte by remember{
            mutableStateOf("Pequeno")
        }
        var sexo by remember{
            mutableStateOf("Macho")
        }
        var castrado by remember{
            mutableStateOf("Não")
        }
        var descricao by remember{
            mutableStateOf("")
        }

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
        val listaEspecie = listOf("Cachorro", "Gato", "Pássaro", "Outros")
        val listaPorte = listOf("Pequeno", "Médio", "Grande")
        val listaSexo = listOf("Macho", "Fêmea")
        val listaCastrado = listOf("Não", "Sim")

        LaunchedEffect(
            key1 = true
        ) {
            CoroutineScope(Main).launch {
                usuarioList = viewModel.verificarLogin()
                usuario = usuarioList[0]
            }
        }

        var adocao = Adocao(
            nome = nome,
            idade = 0,
            especie = especie,
            porte = porte,
            sexo = sexo,
            castrado = false,
            descricao = descricao,
            adotado = false,
            endereco = usuario.endereco,
            imagemURL = imagem,
            idCliente = usuario.idCliente
        )
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding(),
            topBar = {
                TopAppBar(
                    windowInsets = WindowInsets(10.dp, 60.dp, 10.dp, 5.dp),
                    title = {
                        Text(
                        text = "Novo Anúncio",
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
                    .padding(30.dp,90.dp,30.dp, 40.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ) {
                Column(
                    modifier = Modifier.padding(0.dp, 30.dp, 0.dp, 110.dp),
                    verticalArrangement = Arrangement.spacedBy(17.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Link da Imagem (opcional)",
                            style = labelStyle
                        )
                        BasicTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(
                                    width = 2.dp,
                                    color = colorResource(R.color.purple_100),
                                    shape = RoundedCornerShape(14.dp)
                                )
                                .padding(20.dp, 12.dp),
                            value = imagem,
                            onValueChange = { imagem = it},
                            textStyle = labelStyle,
                            singleLine = true,
                            cursorBrush = Brush.verticalGradient(listOf(Purple100, Purple100)),
                        )
                    }
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Nome",
                            style = labelStyle
                        )
                        BasicTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(
                                    width = 2.dp,
                                    color = colorResource(R.color.purple_100),
                                    shape = RoundedCornerShape(14.dp)
                                )
                                .padding(20.dp, 12.dp),
                            value = nome,
                            onValueChange = { nome = it},
                            textStyle = labelStyle,
                            singleLine = true,
                            cursorBrush = Brush.verticalGradient(listOf(Purple100, Purple100)),
                        )
                    }
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Idade",
                            style = labelStyle
                        )
                        BasicTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(
                                    width = 2.dp,
                                    color = colorResource(R.color.purple_100),
                                    shape = RoundedCornerShape(14.dp)
                                )
                                .padding(20.dp, 12.dp),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number
                            ),
                            value = idade,
                            onValueChange = { idade = it},
                            textStyle = labelStyle,
                            singleLine = true,
                            cursorBrush = Brush.verticalGradient(listOf(Purple100, Purple100)),
                        )
                    }
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Especie",
                            style = labelStyle
                        )
                        DropdownMenuBox(listaEspecie) {
                            especie = it
                        }
                    }
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Porte",
                            style = labelStyle
                        )
                        DropdownMenuBox(listaPorte) {
                            porte = it
                        }
                    }
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Sexo",
                            style = labelStyle
                        )
                        DropdownMenuBox(listaSexo) {
                            sexo = it
                        }
                    }

                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Castrado?",
                            style = labelStyle
                        )
                        DropdownMenuBox(listaCastrado) {
                            castrado = it
                        }
                    }
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Descrição",
                            style = labelStyle
                        )
                        BasicTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(
                                    width = 2.dp,
                                    color = colorResource(R.color.purple_100),
                                    shape = RoundedCornerShape(14.dp)
                                )
                                .padding(20.dp, 12.dp),
                            value = descricao,
                            onValueChange = { descricao = it},
                            textStyle = labelStyle,
                            minLines = 4,
                            cursorBrush = Brush.verticalGradient(listOf(Purple100, Purple100)),
                        )
                    }

                    Button(
                        onClick = {

                            if(nome.isEmpty() || idade.isEmpty() || especie == "" || porte == "" || sexo == "" || castrado == "" || descricao.isEmpty()){
                                Toast.makeText(mainActivity, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
                            }
                                    else{
                                        if(idade.toIntOrNull() == null){
                                            Toast.makeText(mainActivity, "Digite um número no campo idade", Toast.LENGTH_SHORT).show()
                                        }
                                        else{
                                            if(idade.toInt() <= 0){
                                                Toast.makeText(mainActivity, "Digite um número positivo no campo idade", Toast.LENGTH_SHORT).show()
                                            }
                                            else{
                                                adocao.idade = idade.toInt()
                                                if(castrado == "Sim"){
                                                    adocao.castrado = true
                                                }
                                                Toast.makeText(mainActivity, "Anuncio criado com sucesso!", Toast.LENGTH_SHORT).show()
                                                viewModel.upsertAdocao(adocao)
                                                navController.navigate(Routes.adote)
                                            }
                                        }

                                    }
                        },
                        modifier = Modifier.size(width = 180.dp, height = 38.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = "Anunciar",
                            style = buttonStyle
                        )
                    }
                }

            }
        }
    }
}

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownMenuBox(itens: List<String>, valor: (String) -> Unit) {
    val context = LocalContext.current
    val isDropDownExpanded = remember {
        mutableStateOf(false)
    }
    val itemPosition = remember {
        mutableStateOf(0)
    }

    var componentWidth by remember { mutableStateOf(0.dp) }

    // get local density from composable
    val density = LocalDensity.current
        Box (
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 2.dp,
                    color = colorResource(R.color.purple_100),
                    shape = RoundedCornerShape(14.dp)
                )
                .onGloballyPositioned {
                    componentWidth = with(density) {
                        it.size.width.toDp()
                    }
                }

                ,
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clickable {
                    isDropDownExpanded.value = true
                }.fillMaxWidth().padding(20.dp, 10.dp)
            ) {
                Text(
                    text = itens[itemPosition.value],
                    style = labelStyle
                )
                Image(
                    painter = painterResource(R.drawable.arrow_down),
                    contentDescription = "DropDown Icon"
                )
            }
            DropdownMenu(
                modifier = Modifier
                    .width(componentWidth)
                    .background(colorResource(R.color.white)),

                expanded = isDropDownExpanded.value,
                onDismissRequest = {
                    isDropDownExpanded.value = false
                }) {
                itens.forEachIndexed { index, username ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = username,
                                style = labelStyle
                            )
                         },
                        onClick = {
                            valor(itens[itemPosition.value])
                            isDropDownExpanded.value = false
                            itemPosition.value = index
                        }
                    )
                    /*
                    HorizontalDivider(
                        color = colorResource(R.color.purple_100),
                        thickness = 1.5.dp,
                    )

                     */
                }
            }
        }

}