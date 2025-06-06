package ru.statisticsapplication.presentation.logic.model.ui

import androidx.compose.runtime.Stable

@Stable
data class AgeModel(
    val ageDiagramModelUIList: List<AgeDiagramModelUI>,
    val countMen: Int,
    val countWomen: Int
)