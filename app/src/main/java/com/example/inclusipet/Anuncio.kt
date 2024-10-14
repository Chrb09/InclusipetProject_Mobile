package com.example.inclusipet

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.inclusipet.ui.theme.GradientPurple50p
import com.example.inclusipet.ui.theme.InclusipetTheme
import com.example.inclusipet.ui.theme.Purple100
import com.example.inclusipet.ui.theme.buttonStyle
import com.example.inclusipet.ui.theme.labelStyle
import com.example.inclusipet.ui.theme.titleStyle
import com.example.inclusipet.viewModel.InclusipetViewModel


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
        var especie by remember{
            mutableStateOf("")
        }
        var porte by remember{
            mutableStateOf("")
        }
        var sexo by remember{
            mutableStateOf("")
        }
        var castrado by remember{
            mutableStateOf("")
        }
        var descricao by remember{
            mutableStateOf("")
        }
        var selectedImageUriList by remember {
            mutableStateOf<List<Uri>>(emptyList())
        }

        val multipleImagePickerLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickMultipleVisualMedia(4),
            onResult = {uriList ->
                selectedImageUriList = uriList
            }
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
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        ) {
                        Button(
                            onClick = {
                                multipleImagePickerLauncher.launch(
                                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                )
                            },
                            modifier = Modifier.height(height = 38.dp),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(
                                text = "Escolher Imagens",
                                style = buttonStyle
                            )
                        }
                        LazyRow(){
                            items(selectedImageUriList){ uri ->
                                AsyncImage(
                                    model = uri,
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(100.dp)
                                        .padding(5.dp)
                                        .clip(shape = RoundedCornerShape(15.dp)),
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }
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
                            value = idade,
                            onValueChange = { idade = it},
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
                            text = "Especie",
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
                            value = especie,
                            onValueChange = { especie = it},
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
                            text = "Porte",
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
                            value = porte,
                            onValueChange = { porte = it},
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
                            text = "Sexo",
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
                            value = sexo,
                            onValueChange = { sexo = it},
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
                            text = "Castrado?",
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
                            value = castrado,
                            onValueChange = { castrado = it},
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

