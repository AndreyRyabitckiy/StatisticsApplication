package ru.statisticsapplication.presentation.logic.model.ui

import androidx.compose.runtime.Stable

@Stable
data class UserUI(
    val id: Long,
    val sex: String,
    val userName: String,
    val isOnline: Boolean,
    val age: Int,
    val avatar: String
)