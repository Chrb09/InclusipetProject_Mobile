package com.example.inclusipet.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.inclusipet.R


// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

val inter = FontFamily(
    Font(R.font.inter_bold, FontWeight.Bold),
    Font(R.font.inter_semibold, FontWeight.SemiBold),
    Font(R.font.inter_medium, FontWeight.Medium),
    Font(R.font.inter_regular, FontWeight.Normal),
)

val buttonStyle = TextStyle(
    fontFamily = inter,
    fontWeight = FontWeight.Bold,
    fontSize = 13.sp,
)
val topBarStyle = TextStyle(
    fontFamily = inter,
    fontSize = 17.sp,
    color = White,
    fontWeight = FontWeight.Bold,
)
val bottomBarStyle = TextStyle(
    fontFamily = inter,
    fontSize = 12.sp,
    fontWeight = FontWeight.SemiBold,
    textAlign = TextAlign.Center
)

val labelStyle = TextStyle(
    fontFamily = inter,
    fontSize = 14.sp,
    color = Grey400,
    fontWeight = FontWeight.Medium,
    textAlign = TextAlign.Left
)

val titleCenterStyle = TextStyle(
    fontFamily = inter,
    fontSize = 26.sp,
    color = Purple100,
    fontWeight = FontWeight.Bold,
    textAlign = TextAlign.Center
)

val titleStyle = TextStyle(
    fontFamily = inter,
    fontSize = 26.sp,
    color = Purple100,
    fontWeight = FontWeight.Bold,
    textAlign = TextAlign.Left
)