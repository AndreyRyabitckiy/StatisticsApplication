package ru.statisticsapplication.domain.repositories

import ru.statisticsapplication.domain.model.UserEntity
import ru.statisticsapplication.domain.model.UserStatisticEntity

interface StatisticRepository {
    suspend fun getUsers(): List<UserEntity>

    suspend fun getStatistic(): List<UserStatisticEntity>
}