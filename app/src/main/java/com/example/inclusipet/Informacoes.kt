package com.example.inclusipet

import android.graphics.BlendMode
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.inclusipet.roomDB.Adocao
import com.example.inclusipet.ui.theme.GradientPurple
import com.example.inclusipet.ui.theme.GradientPurple50p
import com.example.inclusipet.ui.theme.InclusipetTheme
import com.example.inclusipet.ui.theme.inter
import com.example.inclusipet.ui.theme.titleStyle
import com.example.inclusipet.ui.theme.topBarStyle
import com.example.inclusipet.viewModel.InclusipetViewModel
import kotlin.math.absoluteValue


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Informacoes(navController: NavController, viewModel: InclusipetViewModel, modifier: Modifier = Modifier, index: Int, mainActivity: MainActivity) {
    InclusipetTheme(darkTheme = false, dynamicColor = false) {
        val layoutDirection = LocalLayoutDirection.current
        var adocaoList by remember{
            mutableStateOf(listOf<Adocao>())
        }

        viewModel.getAdocaoCod(viewModel.getSelectedAdocao()).observe(mainActivity) {
            adocaoList = it
        }

        var adocao = adocaoList[0]

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
                adocao.imagemUri1.toUri(),
                adocao.imagemUri2.toUri(),
                adocao.imagemUri3.toUri(),
                adocao.imagemUri4.toUri()
            )
            val pagerState = rememberPagerState(
                pageCount = {
                4
            })
            var selectedImageUriList by remember {
                mutableStateOf<List<Uri>>(photos)
            }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(0.dp,0.dp,0.dp, 100.dp)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.Start,
                ) {
                    /*
                    HorizontalPager(
                        modifier = Modifier.fillMaxWidth().padding(0.dp, 60.dp, 0.dp, 0.dp).height(300.dp),
                        state = pagerState,
                        key = { selectedImageUriList[it] }
                    ) { index ->
                        AsyncImage(
                            model = selectedImageUriList[index],
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
                    HorizontalPagerIndicator(
                        pageCount = 4,
                        currentPage = pagerState.currentPage,
                        targetPage = pagerState.targetPage,
                        currentPageOffsetFraction = pagerState.currentPageOffsetFraction
                    )

                     */
                    Column( modifier = Modifier
                        .fillMaxSize()
                        .padding(30.dp,20.dp,30.dp, 60.dp),
                        verticalArrangement = Arrangement.spacedBy(14.dp),
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

@Composable
private fun HorizontalPagerIndicator(
    pageCount: Int,
    currentPage: Int,
    targetPage: Int,
    currentPageOffsetFraction: Float,
    modifier: Modifier = Modifier,
    indicatorColor: Color = colorResource(R.color.white),
    unselectedIndicatorSize: Dp = 15.dp,
    selectedIndicatorSize: Dp = 15.dp,
    indicatorCornerRadius: Dp = 20.dp,
    indicatorPadding: Dp = 3.dp
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .wrapContentSize()
            .height(selectedIndicatorSize + indicatorPadding * 2).fillMaxWidth().offset(y = -(30.dp))
    ) {

        // draw an indicator for each page
        repeat(pageCount) { page ->
            // calculate color and size of the indicator
            val (color, size) =
                if (currentPage == page || targetPage == page) {
                    // calculate page offset
                    val pageOffset =
                        ((currentPage - page) + currentPageOffsetFraction).absoluteValue
                    // calculate offset percentage between 0.0 and 1.0
                    val offsetPercentage = 1f - pageOffset.coerceIn(0f, 1f)

                    val size =
                        unselectedIndicatorSize + ((selectedIndicatorSize - unselectedIndicatorSize) * offsetPercentage)

                    indicatorColor.copy() to size
                } else {
                    indicatorColor.copy(alpha = 0.5f) to unselectedIndicatorSize
                }

            // draw indicator
            Box(
                modifier = Modifier
                    .padding(
                        // apply horizontal padding, so that each indicator is same width
                        horizontal = ((selectedIndicatorSize + indicatorPadding * 2) - size) / 2,
                        vertical = size / 4
                    )
                    .clip(RoundedCornerShape(indicatorCornerRadius))
                    .background(color)
                    .width(size)
                    .height(size / 2)
            )
        }
    }
}