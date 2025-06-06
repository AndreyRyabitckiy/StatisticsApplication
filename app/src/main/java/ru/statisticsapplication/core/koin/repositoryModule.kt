package ru.statisticsapplication.core.koin

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.statisticsapplication.data.repositories.StatisticRepositoryImpl
import ru.statisticsapplication.domain.repositories.StatisticRepository

val repositoryModule = module {
    singleOf(::StatisticRepositoryImpl) bind StatisticRepository::class
}