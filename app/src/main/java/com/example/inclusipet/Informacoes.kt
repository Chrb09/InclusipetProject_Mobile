package com.example.inclusipet

import android.graphics.BlendMode
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.inclusipet.ui.theme.GradientPurple
import com.example.inclusipet.ui.theme.GradientPurple50p
import com.example.inclusipet.ui.theme.InclusipetTheme
import com.example.inclusipet.ui.theme.inter
import com.example.inclusipet.ui.theme.titleStyle
import com.example.inclusipet.ui.theme.topBarStyle


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Informacoes(navController: NavController, modifier: Modifier = Modifier, index: Int) {
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
                            text = "Informações",
                            style = topBarStyle
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.navigate(Routes.adote) }) {
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
            },
            bottomBar = {
                TabView(navController, index)
            }
        ) {
            it.calculateTopPadding()
            val photos = listOf(
                R.drawable.info_placeholder1,
                R.drawable.info_placeholder2,
                R.drawable.info_placeholder3,
                R.drawable.info_placeholder4
            )
            val pagerState = rememberPagerState(pageCount = {
                4
            })
            Column {
                HorizontalPager(
                    modifier = Modifier.fillMaxWidth().padding(0.dp, 75.dp, 0.dp, 0.dp).height(300.dp),
                    state = pagerState,
                    key = { photos[it] }
                ) { index ->
                    Image(
                        painter = painterResource(photos[index]),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize().drawWithCache {
                            val gradient = Brush.verticalGradient(
                                colors = listOf(Color.Transparent, GradientPurple50p),
                                startY = size.height / 3,
                                endY = size.height
                            )
                            onDrawWithContent {
                                drawContent()
                                drawRect(gradient)
                            }
                        }
                    )

                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(30.dp,0.dp,30.dp, 100.dp)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.Start,
                ) {
                    Column( modifier = Modifier
                        .fillMaxSize()
                        .padding(0.dp,20.dp,0.dp, 60.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    )
                    {
                        Text(
                            text = "Fonseca",
                            style = titleStyle
                        )
                        Text(
                            text = "Canino | Macho | 15 Anos | Grande | Castrado",
                            style = TextStyle(
                                color = colorResource(R.color.grey_100),
                                fontSize = 13.sp,
                                fontFamily = inter,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Text(
                            text = "Quem é Fonseca?",
                            fontSize = 26.sp,
                            style = titleStyle
                        )
                        Text(
                            text = "Um cachorro de olhos marrons claros, de porte médio, velho, de pelagem curta, branca e com manchas pretas pelo seu corpo.",
                            fontSize = 13.sp,
                            style = TextStyle(
                                color = colorResource(R.color.grey_400),
                                fontSize = 13.sp,
                                fontFamily = inter,
                                fontWeight = FontWeight.Medium
                            )
                        )
                        Text(
                            text = "Quer Adotar?",
                            fontSize = 26.sp,
                            style = titleStyle
                        )
                        Column(){
                            Text(
                                text = "Miguel Yudi Baba",
                                fontSize = 13.sp,
                                style = TextStyle(
                                    color = colorResource(R.color.grey_400),
                                    fontSize = 13.sp,
                                    fontFamily = inter,
                                    fontWeight = FontWeight.Medium
                                )
                            )
                            Text(
                                text = "(11) 11111-1111",
                                fontSize = 13.sp,
                                style = TextStyle(
                                    color = colorResource(R.color.grey_400),
                                    fontSize = 13.sp,
                                    fontFamily = inter,
                                    fontWeight = FontWeight.Medium
                                )
                            )
                            Text(
                                text = "Av. Aguia de Haia - SP",
                                fontSize = 13.sp,
                                style = TextStyle(
                                    color = colorResource(R.color.grey_400),
                                    fontSize = 13.sp,
                                    fontFamily = inter,
                                    fontWeight = FontWeight.Medium
                                )
                            )
                        }
                    }

                }
            }


        }
    }
}