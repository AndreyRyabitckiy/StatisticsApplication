package ru.statisticsapplication.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight.Companion.W500
import androidx.compose.ui.text.font.FontWeight.Companion.W600
import androidx.compose.ui.text.font.FontWeight.Companion.W700
import androidx.compose.ui.unit.sp

val Typography = Typography(
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        fontWeight = W700
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 20.sp,
        lineHeight = 20.sp,
        fontWeight = W700
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 15.sp,
        lineHeight = 17.sp,
        fontWeight = W500
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 15.sp,
        lineHeight = 16.sp,
        fontWeight = W600
    ),

    bodySmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 13.sp,
        lineHeight = 16.sp,
        fontWeight = W500
    ),

    labelLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 11.sp,
        lineHeight = 11.sp,
        fontWeight = W500
    ),

    labelMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 10.sp,
        lineHeight = 11.sp,
        fontWeight = W500
    )
)