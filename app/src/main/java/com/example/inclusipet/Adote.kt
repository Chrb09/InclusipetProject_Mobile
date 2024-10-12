package com.example.inclusipet

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.inclusipet.ui.theme.GradientPurple
import com.example.inclusipet.ui.theme.InclusipetTheme
import com.example.inclusipet.ui.theme.Purple100
import com.example.inclusipet.ui.theme.bottomBarStyle
import com.example.inclusipet.ui.theme.buttonStyle
import com.example.inclusipet.ui.theme.inter
import com.example.inclusipet.ui.theme.titleStyle
import com.example.inclusipet.ui.theme.topBarStyle


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Adote(navController: NavController, modifier: Modifier = Modifier, index: Int) {
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
                        text = "Adote Hoje!",
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
                    adoptCard("Fonseca", "São Paulo - SP", "Um cachorro de olhos marrons claros, de porte médio, velho, de pelagem curta...", "15 Anos", "Macho", "Castrado", R.drawable.card_placeholder, navController)
                    adoptCard("Afrodite", "São Paulo - SP", "Gata jovem de olhos azuis de pelagem dourada, nascida sem as pernas traseiras...", "7 Anos", "Fêmea", "Não Castrado", R.drawable.card_placeholder2, navController)

                }

            }

            }
        }
    }

@Composable
fun TabView( navController: NavController, index: Int) {
    var selectedTabIndex by rememberSaveable {
        mutableIntStateOf(index)
    }

    NavigationBar(
        modifier = Modifier.padding(0.dp),
        containerColor = colorResource(R.color.purple_100),
    ) {
        // looping over each tab to generate the views and navigation for each item
            NavigationBarItem(
                selected = selectedTabIndex == 0,
                onClick = {
                    navController.navigate(Routes.adote)
                },
                icon = {
                    TabBarIconView(
                        isSelected = selectedTabIndex == 0,
                        selectedIcon = Icons.Rounded.Home,
                        unselectedIcon = Icons.Outlined.Home,
                        title = "Adote"
                    )
                },
                label = {
                    Text(
                        text = "Adote",
                        style = bottomBarStyle
                    )
                },
                colors = NavigationBarItemColors(
                    selectedIconColor = colorResource(R.color.white),
                    selectedTextColor = colorResource(R.color.white),
                    unselectedIconColor = colorResource(R.color.white_200),
                    unselectedTextColor = colorResource(R.color.white_200),
                    disabledIconColor = colorResource(R.color.white_200),
                    disabledTextColor = colorResource(R.color.white_200),
                    selectedIndicatorColor = colorResource(R.color.purple_100)
                )
            )
            NavigationBarItem(
                selected = selectedTabIndex == 1,
                onClick = {
                    navController.navigate(Routes.anuncio)
                },
                icon = {
                    TabBarIconView(
                        isSelected = selectedTabIndex == 1,
                        selectedIcon = Icons.Outlined.AddCircle,
                        unselectedIcon = Icons.Rounded.Add,
                        title = "Novo Anúncio"
                    )
                },
                label = {
                    Text(
                        text = "Novo Anúncio",
                        style = bottomBarStyle
                    )
                },
                colors = NavigationBarItemColors(
                    selectedIconColor = colorResource(R.color.white),
                    selectedTextColor = colorResource(R.color.white),
                    unselectedIconColor = colorResource(R.color.white_200),
                    unselectedTextColor = colorResource(R.color.white_200),
                    disabledIconColor = colorResource(R.color.white_200),
                    disabledTextColor = colorResource(R.color.white_200),
                    selectedIndicatorColor = colorResource(R.color.purple_100)
                )
            )
            NavigationBarItem(
                selected = selectedTabIndex == 2,
                onClick = {
                    navController.navigate(Routes.perfil)
                },
                icon = {
                    TabBarIconView(
                        isSelected = selectedTabIndex == 2,
                        selectedIcon = Icons.Rounded.AccountCircle,
                        unselectedIcon = Icons.Outlined.AccountCircle,
                        title = "Perfil"
                    )
                },
                label = {
                    Text(
                        text = "Perfil",
                        style = bottomBarStyle
                    )
                },
                colors = NavigationBarItemColors(
                    selectedIconColor = colorResource(R.color.white),
                    selectedTextColor = colorResource(R.color.white),
                    unselectedIconColor = colorResource(R.color.white_200),
                    unselectedTextColor = colorResource(R.color.white_200),
                    disabledIconColor = colorResource(R.color.white_200),
                    disabledTextColor = colorResource(R.color.white_200),
                    selectedIndicatorColor = colorResource(R.color.purple_100)
                )
            )
    }
}

@Composable
fun TabBarIconView(
    isSelected: Boolean,
    selectedIcon: ImageVector,
    unselectedIcon: ImageVector,
    title: String,
) {
        Icon(
            modifier = Modifier.size(34.dp),
            imageVector = if (isSelected) {selectedIcon} else {unselectedIcon},
            contentDescription = title
        )
    }

@Composable
fun adoptCard(nome: String, endereco: String, descricao: String, idade: String, sexo: String, castrado: String, image: Int, navController: NavController){
    var sizeImage by remember { mutableStateOf(IntSize.Zero) }
    Box(
        modifier = Modifier.fillMaxWidth().height(450.dp)
            .clip(shape = RoundedCornerShape(15.dp))
            .clickable(
                onClick = {
                    navController.navigate(Routes.informacoes)
                }
            ),
        contentAlignment = Alignment.BottomCenter,

        ){
        Image(painter = painterResource(image),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier.onGloballyPositioned {
                sizeImage = it.size
            }.fillMaxSize())
        Box(modifier = Modifier.matchParentSize().background(
            Brush.verticalGradient(
            colors = listOf(Color.Transparent, GradientPurple),
            startY = sizeImage.height.toFloat()/3,  // 1/3
            endY = sizeImage.height.toFloat()
        )))
        Column(
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(15.dp, 20.dp
                ),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Column(){
                Text(
                    text = nome,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 22.sp,
                        fontFamily = inter,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = endereco,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp,
                        fontFamily = inter,
                        fontWeight = FontWeight.Medium
                    )
                )
            }

            Text(
                text = descricao,
                style = TextStyle(
                    color = Color.White,
                    fontSize = 14.sp,
                    fontFamily = inter,
                    fontWeight = FontWeight.Normal
                )
            )
            Row(
                modifier = Modifier.padding(0.dp, 8.dp,  0.dp, 0.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ){
                Box(
                    modifier = Modifier

                        .clip(RoundedCornerShape(90.dp))
                        .background(Color.White)
                        .padding(15.dp, 8.dp),

                ){
                    Text(
                        text = idade,
                        style = TextStyle(
                            color = colorResource(R.color.purple_100),
                            fontSize = 14.sp,
                            fontFamily = inter,
                            fontWeight = FontWeight.SemiBold
                            )
                    )
                }
                Box(
                    modifier = Modifier

                        .clip(RoundedCornerShape(90.dp))
                        .background(Color.White)
                        .padding(15.dp, 8.dp),

                    ){
                    Text(
                        text = sexo,
                        style = TextStyle(
                            color = colorResource(R.color.purple_100),
                            fontSize = 14.sp,
                            fontFamily = inter,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }
                Box(
                    modifier = Modifier

                        .clip(RoundedCornerShape(90.dp))
                        .background(Color.White)
                        .padding(15.dp, 8.dp),

                    ){
                    Text(
                        text = castrado,
                        style = TextStyle(
                            color = colorResource(R.color.purple_100),
                            fontSize = 14.sp,
                            fontFamily = inter,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }
            }
        }
    }
}