package ru.statisticsapplication.presentation.logic.model.ui

import androidx.compose.runtime.Stable

@Stable
data class VisitorsAndDateModelUI(
    val count: Int,
    val countMessage: String,
    val downMessage: String,
    val tapMessage: String
)