package ru.statisticsapplication.presentation.logic.model.mvi

import androidx.compose.runtime.Stable
import ru.statisticsapplication.presentation.logic.model.ui.AgeDiagramModelUI
import ru.statisticsapplication.presentation.logic.model.ui.ChipModelUI
import ru.statisticsapplication.presentation.logic.model.ui.ChipTypeUI
import ru.statisticsapplication.presentation.logic.model.ui.UserStatisticUI
import ru.statisticsapplication.presentation.logic.model.ui.UserUI
import ru.statisticsapplication.presentation.logic.model.ui.VisitorsAndDateModelUI

@Stable
data class StatisticsUiState(
    val countSubscribe: Int,
    val countUnsubscribe: Int,
    val countNewViews: Int,
    val listUsers: List<UserUI>,
    val listUsersFrequencyOfVisits: List<UserUI>,
    val selectedTimeGenderAndAge: ChipTypeUI,
    val selectedTimeVisitorsTime: ChipTypeUI,
    val chipsGenderAndAge: List<ChipModelUI>,
    val chipsVisitorsTime: List<ChipModelUI>,
    val menCount: Int,
    val womenCount: Int,
    val ageStatistics: List<AgeDiagramModelUI>,
    val statistics: List<UserStatisticUI>,
    val listRoundVisitors: List<VisitorsAndDateModelUI>
) {
    companion object {
        fun getDefault() = StatisticsUiState(
            countUnsubscribe = 0,
            countSubscribe = 0,
            countNewViews = 0,
            listUsers = emptyList(),
            listUsersFrequencyOfVisits = emptyList(),
            statistics = emptyList(),
            selectedTimeGenderAndAge = ChipTypeUI.DAY,
            selectedTimeVisitorsTime = ChipTypeUI.DAY,
            chipsVisitorsTime = listOf(
                ChipModelUI(
                    name = "По дням",
                    isSelected = true,
                    type = ChipTypeUI.DAY
                ),
                ChipModelUI(
                    name = "По неделям",
                    isSelected = false,
                    type = ChipTypeUI.WEEK
                ),
                ChipModelUI(
                    name = "По месяцам",
                    isSelected = false,
                    type = ChipTypeUI.MONTH
                ),
            ),
            chipsGenderAndAge = listOf(
                ChipModelUI(
                    name = "Сегодня",
                    isSelected = true,
                    type = ChipTypeUI.DAY
                ),
                ChipModelUI(
                    name = "Неделя",
                    isSelected = false,
                    type = ChipTypeUI.WEEK
                ),
                ChipModelUI(
                    name = "Месяц",
                    isSelected = false,
                    type = ChipTypeUI.MONTH
                ),
                ChipModelUI(
                    name = "Все время",
                    isSelected = false,
                    type = ChipTypeUI.ALL_TIME
                )
            ),
            menCount = 0,
            womenCount = 0,
            listRoundVisitors = emptyList(),
            ageStatistics = listOf(
                AgeDiagramModelUI(
                    message = "18-21",
                    womenPercent = 0,
                    menPercent = 0,
                    countMen = 0,
                    countWomen = 0,
                    ageStart = 18,
                    ageEnd = 21
                ),
                AgeDiagramModelUI(
                    message = "22-25",
                    womenPercent = 0,
                    menPercent = 0,
                    countMen = 0,
                    countWomen = 0,
                    ageStart = 22,
                    ageEnd = 25
                ),
                AgeDiagramModelUI(
                    message = "26-30",
                    womenPercent = 0,
                    menPercent = 0,
                    countMen = 0,
                    countWomen = 0,
                    ageStart = 26,
                    ageEnd = 30
                ),
                AgeDiagramModelUI(
                    message = "31-35",
                    womenPercent = 0,
                    menPercent = 0,
                    countMen = 0,
                    countWomen = 0,
                    ageStart = 31,
                    ageEnd = 35
                ),
                AgeDiagramModelUI(
                    message = "36-40",
                    womenPercent = 0,
                    menPercent = 0,
                    countMen = 0,
                    countWomen = 0,
                    ageStart = 36,
                    ageEnd = 40
                ),
                AgeDiagramModelUI(
                    message = "40-50",
                    womenPercent = 0,
                    menPercent = 0,
                    countMen = 0,
                    countWomen = 0,
                    ageStart = 40,
                    ageEnd = 50
                ),
                AgeDiagramModelUI(
                    message = ">50",
                    womenPercent = 0,
                    menPercent = 0,
                    countMen = 0,
                    countWomen = 0,
                    ageStart = 50,
                    ageEnd = 150
                ),
            )
        )
    }
}