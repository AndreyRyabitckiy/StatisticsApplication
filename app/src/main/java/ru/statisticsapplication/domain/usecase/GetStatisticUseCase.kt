package ru.statisticsapplication.domain.usecase

import ru.statisticsapplication.domain.repositories.StatisticRepository

class GetStatisticUseCase(
    private val statisticRepository: StatisticRepository
) {
    suspend operator fun invoke() = statisticRepository.getStatistic()
}