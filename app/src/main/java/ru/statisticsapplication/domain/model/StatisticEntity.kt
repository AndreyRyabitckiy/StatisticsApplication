package ru.statisticsapplication.domain.model

data class UserStatisticEntity(
    val userId: Long,
    val type: StatisticTypeEntity,
    val dates: List<String>
)

enum class StatisticTypeEntity {
    VIEW,
    SUBSCRIPTION,
    UN_SUBSCRIPTION;
}
