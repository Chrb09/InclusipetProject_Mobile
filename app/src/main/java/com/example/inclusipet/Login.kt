package com.example.inclusipet

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.inclusipet.ui.theme.InclusipetTheme
import com.example.inclusipet.ui.theme.buttonStyle
import com.example.inclusipet.ui.theme.inter
import com.example.inclusipet.ui.theme.titleCenterStyle

@Composable
fun Login(navController: NavController, modifier: Modifier = Modifier) {
    InclusipetTheme(darkTheme = false, dynamicColor = false) {
        val layoutDirection = LocalLayoutDirection.current
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding(),
            topBar = {

            }
        ) {
            it.calculateTopPadding()
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp, 50.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Bem vindo de volta!",
                    style = titleCenterStyle,

                )
                Button(
                    onClick = {
                        navController.navigate(Routes.index)
                    },
                    modifier = Modifier.size(width = 200.dp, height = 48.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.white),
                        contentColor = colorResource(R.color.grey_100)
                    ),
                    border = BorderStroke(2.5.dp, colorResource(R.color.grey_100))
                ) {
                    Text(
                        text = "Voltar",
                        style = buttonStyle
                    )
                }
            }
        }
    }
}

