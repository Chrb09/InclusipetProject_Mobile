package com.example.inclusipet

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.inclusipet.ui.theme.InclusipetTheme
import com.example.inclusipet.ui.theme.buttonStyle
import com.example.inclusipet.ui.theme.inter

@Composable
fun Index(navController: NavController, modifier: Modifier = Modifier) {
    InclusipetTheme(darkTheme = false, dynamicColor = false) {
        val layoutDirection = LocalLayoutDirection.current
    Box(
        modifier = Modifier
            .statusBarsPadding()
            .padding(
                start = WindowInsets.safeDrawing
                    .asPaddingValues()
                    .calculateStartPadding(layoutDirection),
                end = WindowInsets.safeDrawing
                    .asPaddingValues()
                    .calculateEndPadding(layoutDirection),
            ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp, 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Column(
                modifier = Modifier.weight(2f),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.inclusipet_logo),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(2f),
                    contentScale = ContentScale.Fit
                )
                Text(
                    text = stringResource(R.string.index_greeting),
                    modifier = Modifier.weight(1f).fillMaxWidth(),
                    style = TextStyle(
                        fontFamily = inter,
                        fontSize = 22.sp,
                        color = colorResource(R.color.grey_400),
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    ),

                    )
            }
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {
                        navController.navigate(Routes.cadastro)
                    },
                    modifier = Modifier.size(width = 200.dp, height = 48.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "Criar conta",
                        style = buttonStyle
                    )
                }
                Button(
                    onClick = {
                        navController.navigate(Routes.login)
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
                        text = "Fazer Login",
                        style = buttonStyle
                    )
                }
            }

        }
    }
}
}

