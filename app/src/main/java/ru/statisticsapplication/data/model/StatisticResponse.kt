package ru.statisticsapplication.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StatisticResponse(
    val statistics: List<UserStatisticResponse>
)

@Serializable
data class UserStatisticResponse(
    @SerialName("user_id")
    val userId: Long,
    val type: String,
    val dates: List<Long>
)
