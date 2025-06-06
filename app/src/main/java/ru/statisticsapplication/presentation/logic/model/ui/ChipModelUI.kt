package ru.statisticsapplication.presentation.logic.model.ui

import androidx.compose.runtime.Stable

@Stable
data class ChipModelUI(
    val name: String,
    val isSelected: Boolean,
    val type: ChipTypeUI
)