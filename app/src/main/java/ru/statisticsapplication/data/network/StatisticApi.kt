package ru.statisticsapplication.data.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import ru.statisticsapplication.data.model.StatisticResponse
import ru.statisticsapplication.data.model.UserDataResponse

class StatisticApi(
    private val client: HttpClient
) {
    suspend fun getUsers(): UserDataResponse = client.get("users").body()

    suspend fun getStatistic(): StatisticResponse = client.get("statistics").body()
}