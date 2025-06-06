package ru.statisticsapplication.domain.model

data class UserEntity(
    val id: Long,
    val sex: String,
    val userName: String,
    val isOnline: Boolean,
    val age: Int,
    val files: List<UserFileEntity>
)

data class UserFileEntity(
    val id: Long,
    val url: String,
    val type: String
)