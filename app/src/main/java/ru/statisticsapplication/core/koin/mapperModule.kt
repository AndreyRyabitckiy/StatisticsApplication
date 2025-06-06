package ru.statisticsapplication.core.koin

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import ru.statisticsapplication.data.mappers.StatisticMapper
import ru.statisticsapplication.presentation.logic.mapper.StatisticMapperUI

val mapperModule = module {
    factoryOf(::StatisticMapper)
    factoryOf(::StatisticMapperUI)
}