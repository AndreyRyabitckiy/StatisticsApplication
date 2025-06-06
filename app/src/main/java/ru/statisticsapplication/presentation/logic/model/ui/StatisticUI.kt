package ru.statisticsapplication.presentation.logic.model.ui

import androidx.compose.runtime.Stable

@Stable
data class UserStatisticUI(
    val userId: Long,
    val type: StatisticTypeUI,
    val dates: List<String>
)

@Stable
enum class StatisticTypeUI {
    VIEW,
    SUBSCRIPTION,
    UN_SUBSCRIPTION;
}
