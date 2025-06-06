package ru.statisticsapplication.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDataResponse(
    val users: List<UserResponse>
)

@Serializable
data class UserResponse(
    val id: Long,
    val sex: String,
    @SerialName("username")
    val userName: String,
    val isOnline: Boolean,
    val age: Int,
    val files: List<UserFileResponse>
)

@Serializable
data class UserFileResponse(
    val id: Long,
    val url: String,
    val type: String
)