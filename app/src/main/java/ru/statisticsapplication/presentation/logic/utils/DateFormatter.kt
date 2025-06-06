package ru.statisticsapplication.presentation.logic.utils

import ru.statisticsapplication.presentation.logic.model.ui.ChipTypeUI
import ru.statisticsapplication.presentation.logic.model.ui.StatisticTypeUI
import ru.statisticsapplication.presentation.logic.model.ui.UserStatisticUI
import ru.statisticsapplication.presentation.logic.model.ui.VisitorsAndDateModelUI
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

class DateFormatter(
    private val utils: SortUtils
) {
    fun getUsersIdsWitchCountWatchFromTime(
        time: ChipTypeUI,
        statistics: List<UserStatisticUI>
    ): Map<Long, Int> {

        val maxDate = statistics.flatMap { it.dates }.map {
            LocalDate.parse(
                it,
                DateTimeFormatter.ofPattern("ddMMyyyy")
            )
        }.maxOf { it }

        return statistics
            .filter { it.type == StatisticTypeUI.VIEW }
            .map { stat ->
                changeListDateInCurrentTime(
                    maxDate = maxDate,
                    time = time,
                    stat = stat
                )
            }
            .filter { it.dates.isNotEmpty() }
            .groupBy { it.userId }
            .mapValues { (_, group) ->
                group.sumOf { it.dates.size }
            }
    }

    fun createListRoundVisitors(
        dates: List<String>,
        time: ChipTypeUI
    ): List<VisitorsAndDateModelUI> {
        val inputFormatter = DateTimeFormatter.ofPattern("ddMMyyyy")
        val dateWithCount = utils.countDuplicates(dates)

        val sortedDates = dateWithCount.entries.map {
            LocalDate.parse(it.key, inputFormatter) to it.value
        }.sortedBy { it.first }

        val maxDate = sortedDates.last().first

        val minDate = when (time) {
            ChipTypeUI.DAY -> maxDate.minusDays(6)
            ChipTypeUI.WEEK -> maxDate.minusWeeks(6)
            ChipTypeUI.MONTH -> maxDate.minusMonths(6)
            ChipTypeUI.ALL_TIME -> sortedDates.first().first
        }

        return when (time) {
            ChipTypeUI.DAY -> {
                generateSequence(minDate) { it.plusDays(1) }
                    .takeWhile { it <= maxDate }
                    .map { date ->
                        val count = dateWithCount[date.format(inputFormatter)] ?: 0
                        VisitorsAndDateModelUI(
                            count = count,
                            downMessage = "${"%02d".format(date.dayOfMonth)}.${"%02d".format(date.monthValue)}",
                            tapMessage = "${date.dayOfMonth} ${
                                date.month.getDisplayName(
                                    TextStyle.FULL,
                                    Locale("ru")
                                )
                            }",
                            countMessage = getCorrectVisitor(count)
                        )
                    }.toList()
            }

            ChipTypeUI.WEEK -> {
                val weeks = generateSequence(minDate) { it.plusWeeks(1) }
                    .takeWhile { it <= maxDate }
                    .toList()

                weeks.mapIndexed { index, weekStart ->
                    val weekEnd =
                        if (index < weeks.size - 1) weeks[index + 1].minusDays(1) else maxDate

                    val count = sortedDates
                        .filter { it.first in weekStart..weekEnd }
                        .sumOf { it.second }

                    VisitorsAndDateModelUI(
                        count = count,
                        downMessage = "${"%02d".format(weekStart.dayOfMonth)}.${
                            "%02d".format(
                                weekStart.monthValue
                            )
                        }",
                        tapMessage = "${weekStart.dayOfMonth} ${
                            weekStart.month.getDisplayName(
                                TextStyle.FULL,
                                Locale("ru")
                            )
                        }",
                        countMessage = getCorrectVisitor(count)
                    )
                }
            }

            ChipTypeUI.MONTH -> {
                val months = generateSequence(minDate) { it.plusMonths(1) }
                    .takeWhile { it <= maxDate }
                    .toList()

                months.map { monthStart ->
                    val count = sortedDates
                        .filter { it.first.year == monthStart.year && it.first.month == monthStart.month }
                        .sumOf { it.second }

                    VisitorsAndDateModelUI(
                        count = count,
                        downMessage = "${"%02d".format(monthStart.dayOfMonth)}.${
                            "%02d".format(
                                monthStart.monthValue
                            )
                        }",
                        tapMessage = "${monthStart.dayOfMonth} ${
                            monthStart.month.getDisplayName(
                                TextStyle.FULL,
                                Locale("ru")
                            )
                        }",
                        countMessage = getCorrectVisitor(count)
                    )
                }
            }

            ChipTypeUI.ALL_TIME -> {
                emptyList()
            }
        }
    }


    private fun getCorrectVisitor(count: Int): String {
        return count.toString() + when {
            count % 100 in 11..14 -> " посетителей"
            count % 10 == 1 -> " посетитель"
            count % 10 in 2..4 -> " посетителя"
            else -> " посетителей"
        }
    }

    private fun changeListDateInCurrentTime(
        time: ChipTypeUI,
        maxDate: LocalDate,
        stat: UserStatisticUI
    ): UserStatisticUI {
        val inputFormatter = DateTimeFormatter.ofPattern("ddMMyyyy")
        val dateList = stat.dates.map { LocalDate.parse(
            it,
            inputFormatter
        ) }.sorted()
        val minDate = when (time) {
            ChipTypeUI.DAY -> maxDate
            ChipTypeUI.WEEK -> maxDate.minusDays(6)
            ChipTypeUI.MONTH -> maxDate.minusDays(30)
            ChipTypeUI.ALL_TIME -> dateList.first()
        }
        val filteredDates = dateList.filter { it in minDate..maxDate }
        return stat.copy(dates = filteredDates.map { inputFormatter.format(it) })
    }
}