package ru.statisticsapplication.presentation.logic.mapper

import ru.statisticsapplication.domain.model.StatisticTypeEntity
import ru.statisticsapplication.domain.model.UserEntity
import ru.statisticsapplication.domain.model.UserStatisticEntity
import ru.statisticsapplication.presentation.logic.model.ui.StatisticTypeUI
import ru.statisticsapplication.presentation.logic.model.ui.UserStatisticUI
import ru.statisticsapplication.presentation.logic.model.ui.UserUI

class StatisticMapperUI {
    fun userEntityToUiModel(userEntity: UserEntity) = UserUI(
        id = userEntity.id,
        sex = userEntity.sex,
        userName = userEntity.userName,
        isOnline = userEntity.isOnline,
        age = userEntity.age,
        avatar = userEntity.files.find { it.type == TYPE_AVATAR }?.url.orEmpty()
    )

    fun userStatisticToUIModel(statisticEntity: UserStatisticEntity) = UserStatisticUI(
        userId = statisticEntity.userId,
        type = statisticEntity.type.toUiModel(),
        dates = statisticEntity.dates
    )

    private fun StatisticTypeEntity.toUiModel(): StatisticTypeUI  = when (this) {
            StatisticTypeEntity.VIEW -> StatisticTypeUI.VIEW
            StatisticTypeEntity.SUBSCRIPTION -> StatisticTypeUI.SUBSCRIPTION
            StatisticTypeEntity.UN_SUBSCRIPTION -> StatisticTypeUI.UN_SUBSCRIPTION
        }


    companion object {
        private const val TYPE_AVATAR = "avatar"
    }
}