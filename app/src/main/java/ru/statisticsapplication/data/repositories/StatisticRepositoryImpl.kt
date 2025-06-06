package ru.statisticsapplication.data.repositories

import ru.statisticsapplication.data.mappers.StatisticMapper
import ru.statisticsapplication.data.network.StatisticApi
import ru.statisticsapplication.domain.model.UserEntity
import ru.statisticsapplication.domain.model.UserStatisticEntity
import ru.statisticsapplication.domain.repositories.StatisticRepository

class StatisticRepositoryImpl(
    private val statisticApi: StatisticApi,
    private val statisticMapper: StatisticMapper
) : StatisticRepository {
    override suspend fun getUsers(): List<UserEntity> =
        statisticApi.getUsers().users.map { statisticMapper.userToEntity(it) }

    override suspend fun getStatistic(): List<UserStatisticEntity> =
        statisticApi.getStatistic().statistics.map { statisticMapper.statisticToEntity(it) }

}