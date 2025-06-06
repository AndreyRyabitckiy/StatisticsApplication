package ru.statisticsapplication.presentation.logic.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.statisticsapplication.domain.usecase.GetStatisticUseCase
import ru.statisticsapplication.domain.usecase.GetUsersUseCase
import ru.statisticsapplication.presentation.logic.mapper.StatisticMapperUI
import ru.statisticsapplication.presentation.logic.model.ui.ChipTypeUI
import ru.statisticsapplication.presentation.logic.model.mvi.StatisticsUiEvent
import ru.statisticsapplication.presentation.logic.model.mvi.StatisticsUiState
import ru.statisticsapplication.presentation.logic.model.ui.StatisticTypeUI
import ru.statisticsapplication.presentation.logic.utils.DateFormatter
import ru.statisticsapplication.presentation.logic.utils.SortUtils

class StatisticsViewModel(
    private val getUsersUseCase: GetUsersUseCase,
    private val getStatisticUseCase: GetStatisticUseCase,
    private val utils: SortUtils,
    private val dateFormatter: DateFormatter,
    private val mapper: StatisticMapperUI
) : ViewModel() {

    private val _state = MutableStateFlow(StatisticsUiState.getDefault())
    val state: StateFlow<StatisticsUiState> = _state.asStateFlow()

    private val _events = MutableSharedFlow<StatisticsUiEvent>()

    init {
        collectEvent()
    }

    fun sendEvent(event: StatisticsUiEvent) {
        viewModelScope.launch { _events.emit(event) }
    }

    private fun collectEvent() {
        viewModelScope.launch {
            _events.collect { event ->
                when (event) {
                    is StatisticsUiEvent.LoadStatisticsAndUsers -> loadData()
                    is StatisticsUiEvent.ClickOnChipTimeGenderAndAge -> selectTimeShowGenderAndAge(
                        event.type
                    )

                    is StatisticsUiEvent.ClickOnChipVisitorsTime -> clickOnChipVisitorsTime(event.type)
                }
            }
        }
    }

    private fun clickOnChipVisitorsTime(type: ChipTypeUI) {
        _state.update {
            _state.value.copy(
                selectedTimeVisitorsTime = type,
                chipsVisitorsTime = _state.value.chipsVisitorsTime.map {
                    it.copy(isSelected = it.type == type)
                },
                listRoundVisitors = dateFormatter.createListRoundVisitors(
                    time = type,
                    dates = _state.value.statistics.flatMap { it.dates }
                ),
            )
        }
    }

    private fun selectTimeShowGenderAndAge(type: ChipTypeUI) {
        _state.update {
            val ageStatistic = utils.createAgeStatistic(
                uiState = _state.value,
                ids = dateFormatter.getUsersIdsWitchCountWatchFromTime(
                    time = type,
                    statistics = _state.value.statistics
                )
            )
            _state.value.copy(
                selectedTimeGenderAndAge = type,
                chipsGenderAndAge = _state.value.chipsGenderAndAge.map {
                    it.copy(isSelected = it.type == type)
                },
                ageStatistics = ageStatistic.ageDiagramModelUIList,
                womenCount = ageStatistic.countWomen,
                menCount = ageStatistic.countMen
            )
        }
    }

    private fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            getUsers()
            getStatistics()
        }
    }

    private suspend fun getUsers() {
        _state.update {
            _state.value.copy(listUsers = getUsersUseCase().map { mapper.userEntityToUiModel(it) })
        }
    }

    private suspend fun getStatistics() {
        val statistics = getStatisticUseCase().map { mapper.userStatisticToUIModel(it) }
        _state.update {
            _state.value.copy(
                statistics = statistics,
                countSubscribe = statistics
                    .filter { it.type == StatisticTypeUI.SUBSCRIPTION }
                    .size,
                countUnsubscribe = statistics
                    .filter { it.type == StatisticTypeUI.UN_SUBSCRIPTION }
                    .size,
                countNewViews = statistics
                    .filter { it.type == StatisticTypeUI.VIEW }
                    .sumOf { it.dates.size },
                listUsersFrequencyOfVisits = utils.createListUsersFrequencyOfVisits(
                    listUsers = _state.value.listUsers,
                    idsUsers = utils.findThreeUsersMostVisited(statistics)
                ),
                menCount = utils.getCountMan(
                    listUsers = _state.value.listUsers,
                    idsUsers = utils.getUsersIdsWitchCountWatch(statistics)
                ),
                womenCount = utils.getCountWoman(
                    listUsers = _state.value.listUsers,
                    idsUsers = utils.getUsersIdsWitchCountWatch(statistics)
                ),
                listRoundVisitors = dateFormatter.createListRoundVisitors(
                    time = _state.value.selectedTimeVisitorsTime,
                    dates = statistics.flatMap { it.dates }
                ),
            )
        }
        selectTimeShowGenderAndAge(type = _state.value.selectedTimeGenderAndAge)
    }
}