package ru.statisticsapplication.data.mappers

import ru.statisticsapplication.data.model.UserFileResponse
import ru.statisticsapplication.data.model.UserResponse
import ru.statisticsapplication.data.model.UserStatisticResponse
import ru.statisticsapplication.domain.model.StatisticTypeEntity
import ru.statisticsapplication.domain.model.UserEntity
import ru.statisticsapplication.domain.model.UserFileEntity
import ru.statisticsapplication.domain.model.UserStatisticEntity

class StatisticMapper {

    fun statisticToEntity(statistic: UserStatisticResponse) = UserStatisticEntity(
        userId = statistic.userId,
        dates = statistic.dates.map {
            if (it.toString().length < 8) "0$it" else it.toString()
        },
        type = when (statistic.type) {
            "subscription" -> StatisticTypeEntity.SUBSCRIPTION
            "unsubscription" -> StatisticTypeEntity.UN_SUBSCRIPTION
            else -> StatisticTypeEntity.VIEW
        }
    )

    fun userToEntity(user: UserResponse) = UserEntity(
        id = user.id,
        sex = user.sex,
        userName = user.userName,
        isOnline = user.isOnline,
        age = user.age,
        files = user.files.map { it.toEntity() }
    )

    private fun UserFileResponse.toEntity() = UserFileEntity(
        id = this.id,
        url = this.url,
        type = this.type
    )
}