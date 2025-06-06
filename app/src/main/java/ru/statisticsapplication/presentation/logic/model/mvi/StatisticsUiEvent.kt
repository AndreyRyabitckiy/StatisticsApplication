package ru.statisticsapplication.presentation.logic.model.mvi

import ru.statisticsapplication.presentation.logic.model.ui.ChipTypeUI

sealed class StatisticsUiEvent {
    data object LoadStatisticsAndUsers : StatisticsUiEvent()
    data class ClickOnChipTimeGenderAndAge(val type: ChipTypeUI) : StatisticsUiEvent()
    data class ClickOnChipVisitorsTime(val type: ChipTypeUI) : StatisticsUiEvent()
}