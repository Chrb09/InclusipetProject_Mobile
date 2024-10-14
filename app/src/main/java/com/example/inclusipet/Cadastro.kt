package com.example.inclusipet

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.inclusipet.roomDB.Usuario
import com.example.inclusipet.ui.theme.InclusipetTheme
import com.example.inclusipet.ui.theme.Purple100
import com.example.inclusipet.ui.theme.buttonStyle
import com.example.inclusipet.ui.theme.labelStyle
import com.example.inclusipet.ui.theme.titleCenterStyle
import com.example.inclusipet.ui.theme.topBarStyle
import com.example.inclusipet.viewModel.InclusipetViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import java.io.Console

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Cadastro(navController: NavController, viewModel: InclusipetViewModel, modifier: Modifier = Modifier, mainActivity: MainActivity) {
    var email by remember{
        mutableStateOf("")
    }
    var senha by remember{
        mutableStateOf("")
    }
    var nome by remember{
        mutableStateOf("")
    }
    var datanasc by remember{
        mutableStateOf("")
    }
    var cpf by remember{
        mutableStateOf("")
    }
    var telefone by remember{
        mutableStateOf("")
    }
    var endereco by remember{
        mutableStateOf("")
    }
    var usuario = Usuario(
        email = email,
        senha = senha,
        nome = nome,
        datanasc = datanasc,
        cpf = cpf,
        telefone = telefone,
        endereco = endereco,
        logado = false
    )
    var usuarioList by remember{
        mutableStateOf(listOf<Usuario>())
    }
    InclusipetTheme(darkTheme = false, dynamicColor = false) {
        val layoutDirection = LocalLayoutDirection.current
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding(),
            topBar = {
                TopAppBar(
                    windowInsets = WindowInsets(10.dp, 30.dp, 10.dp, 5.dp),
                    title = {
                        Text(
                        text = "Cadastro",
                            style = topBarStyle
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.navigate(Routes.index) }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                tint = colorResource(R.color.white),
                                contentDescription = "Voltar"
                            )
                        }
                    },
                    actions = {
                        Image(
                            painter = painterResource(R.drawable.inclusipet_topbar),
                            modifier = Modifier.size(70.dp).padding(end = 20.dp, bottom = 10.dp),
                            contentDescription = null,
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(colorResource(R.color.purple_100))
                )
            }
        ) {
            it.calculateTopPadding()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(30.dp,75.dp,30.dp, 40.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ) {
                Column(
                    modifier = Modifier.padding(0.dp, 20.dp, 0.dp, 25.dp),
                    verticalArrangement = Arrangement.spacedBy(17.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Crie sua conta",
                        style = titleCenterStyle,
                        )
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Email",
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
                            value = email,
                            onValueChange = { email = it},
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
                            text = "Senha",
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
                            value = senha,
                            onValueChange = { senha = it},
                            textStyle = labelStyle,
                            singleLine = true,
                            cursorBrush = Brush.verticalGradient(listOf(Purple100, Purple100)),
                            visualTransformation = PasswordVisualTransformation(),
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
                            text = "Data Nascimento",
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
                            value = datanasc,
                            onValueChange = { datanasc = it},
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
                            text = "CPF",
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
                            value = cpf,
                            onValueChange = { cpf = it},
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
                            text = "Telefone",
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
                            value = telefone,
                            onValueChange = { telefone = it},
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
                            text = "Endereço",
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
                            value = endereco,
                            onValueChange = { endereco = it},
                            textStyle = labelStyle,
                            singleLine = true,
                            cursorBrush = Brush.verticalGradient(listOf(Purple100, Purple100)),
                        )
                    }

                    Button(
                        onClick = {
                            if(email.isEmpty() || senha.isEmpty() || nome.isEmpty() || datanasc.isEmpty() || cpf.isEmpty() || telefone.isEmpty() || endereco.isEmpty()){
                                Toast.makeText(mainActivity, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
                            }else{
                                    CoroutineScope(Main).launch {
                                        usuarioList = viewModel.verificarEmail(email)

                                        if(usuarioList.isNotEmpty()){
                                            Toast.makeText(mainActivity, "Email já cadastrado", Toast.LENGTH_SHORT).show()
                                        }
                                        else{
                                            navController.navigate(Routes.index)
                                            Toast.makeText(mainActivity, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show()
                                            viewModel.upsertUsuario(usuario)

                                        }

                                    }


                                }


                        },
                        modifier = Modifier.size(width = 180.dp, height = 38.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = "Cadastrar-se",
                            style = buttonStyle
                        )
                    }
                }

            }
        }
    }
}

