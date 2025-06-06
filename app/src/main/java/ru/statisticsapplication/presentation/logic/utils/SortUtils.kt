package ru.statisticsapplication.presentation.logic.utils

import ru.statisticsapplication.presentation.logic.model.mvi.StatisticsUiState
import ru.statisticsapplication.presentation.logic.model.ui.AgeDiagramModelUI
import ru.statisticsapplication.presentation.logic.model.ui.AgeModel
import ru.statisticsapplication.presentation.logic.model.ui.StatisticTypeUI
import ru.statisticsapplication.presentation.logic.model.ui.UserStatisticUI
import ru.statisticsapplication.presentation.logic.model.ui.UserUI

class SortUtils {
    fun getCountMan(
        listUsers:List<UserUI>,
        idsUsers: Map<Long, Int>
    ): Int {
        val menList = listUsers.filter { it.sex == GENDER_MAN }.map { it.id }
        return idsUsers.filter { it.key in menList }.entries.sumOf { it.value }
    }

    fun getCountWoman(
        listUsers:List<UserUI>,
        idsUsers: Map<Long, Int>
    ): Int {
        val womenList = listUsers.filter { it.sex == GENDER_WOMAN }.map { it.id }
        return idsUsers.filter { it.key in womenList }.entries.sumOf { it.value }
    }

    fun countDuplicates(dates: List<String>): Map<String, Int> {
        return dates.groupingBy { it }.eachCount()
    }

    fun createListUsersFrequencyOfVisits(
        listUsers:List<UserUI>,
        idsUsers: List<Long>
    ): List<UserUI> {
        return ArrayList<UserUI>().apply {
            idsUsers.forEach { id ->
                listUsers
                    .find { user -> user.id == id }
                    ?.let { add(it) }
            }
        }
    }

    fun getUsersIdsWitchCountWatch(statistics: List<UserStatisticUI>): Map<Long, Int> {
        return statistics
            .filter { it.type == StatisticTypeUI.VIEW }
            .groupBy { it.userId }
            .mapValues { (_, group) ->
                group.sumOf { it.dates.size }
            }
    }

    fun findThreeUsersMostVisited(statistics: List<UserStatisticUI>): List<Long> {
        return statistics
            .asSequence()
            .filter { it.type == StatisticTypeUI.VIEW }
            .sortedByDescending { it.dates.size }
            .map { it.userId }
            .distinct()
            .take(3)
            .toList()
    }

    private fun checkAge(
        countNewViewsAll: Int,
        countNewViews: Int,
        model: AgeDiagramModelUI,
        user: UserUI
    ): AgeDiagramModelUI {
        if (countNewViewsAll == 0) return model
        val isInAgeSize = user.age in model.ageStart..model.ageEnd

        return when (user.sex) {
            GENDER_MAN -> {
                if (isInAgeSize) model.copy(
                    countMen = model.countMen + countNewViews,
                    menPercent = (model.countMen + countNewViews) * 100 / countNewViewsAll
                ) else model
            }
            GENDER_WOMAN -> if (isInAgeSize) model.copy(
                countWomen = model.countWomen + countNewViews,
                womenPercent = (model.countWomen + countNewViews) * 100 / countNewViewsAll
            ) else model
            else -> model
        }
    }

    fun createAgeStatistic(
        uiState: StatisticsUiState,
        ids: Map<Long, Int>
    ): AgeModel {
        val updatedStatistics = uiState.ageStatistics
            .map {
                it.copy(
                    countMen = 0,
                    countWomen = 0,
                    womenPercent = 0,
                    menPercent = 0
                )
            }.toMutableList()
        val maxCount = ids.map { it.value }.sum()

        ids.forEach { id ->
            val user = uiState.listUsers.find { it.id == id.key }
            user?.let { u ->
                updatedStatistics.replaceAll { model ->
                    checkAge(
                        countNewViewsAll = maxCount,
                        model = model,
                        user = u,
                        countNewViews = id.value
                    )
                }
            }
        }
        return AgeModel(
            countWomen = updatedStatistics.sumOf { it.countWomen },
            countMen = updatedStatistics.sumOf { it.countMen },
            ageDiagramModelUIList = updatedStatistics.toList()
        )
    }

    companion object {
        private const val GENDER_MAN = "M"
        private const val GENDER_WOMAN = "W"
    }
}