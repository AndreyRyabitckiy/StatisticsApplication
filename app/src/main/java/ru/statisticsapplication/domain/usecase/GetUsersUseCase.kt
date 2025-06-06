package ru.statisticsapplication.domain.usecase

import ru.statisticsapplication.domain.repositories.StatisticRepository

class GetUsersUseCase(
    private val statisticRepository: StatisticRepository
) {
    suspend operator fun invoke() = statisticRepository.getUsers()
}