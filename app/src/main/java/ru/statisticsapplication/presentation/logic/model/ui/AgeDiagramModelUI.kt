package ru.statisticsapplication.presentation.logic.model.ui

import androidx.compose.runtime.Stable

@Stable
data class AgeDiagramModelUI(
    val message: String,
    val ageStart: Int,
    val ageEnd: Int,
    val countMen: Int,
    val countWomen: Int,
    val womenPercent: Int,
    val menPercent: Int
)