package com.example.inclusipet

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.inclusipet.ui.theme.InclusipetTheme
import com.example.inclusipet.ui.theme.buttonStyle
import com.example.inclusipet.ui.theme.labelStyle
import com.example.inclusipet.ui.theme.titleCenterStyle
import com.example.inclusipet.ui.theme.topBarStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Cadastro(navController: NavController, modifier: Modifier = Modifier) {
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
                    .padding(30.dp,90.dp,30.dp, 50.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
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
                        TextField(
                            modifier = Modifier.fillMaxWidth().border(width = 2.5.dp, color = colorResource(R.color.purple_100), shape = RoundedCornerShape(14.dp)),
                            value = email,
                            onValueChange = { email = it },
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = Color.Transparent,
                                focusedContainerColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                cursorColor = colorResource(R.color.purple_100)
                            ),

                            shape = RoundedCornerShape(20.dp)
                        )
                    }

                    Button(
                        onClick = {
                            navController.navigate(Routes.cadastro)
                        },
                        modifier = Modifier.size(width = 200.dp, height = 48.dp),
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

