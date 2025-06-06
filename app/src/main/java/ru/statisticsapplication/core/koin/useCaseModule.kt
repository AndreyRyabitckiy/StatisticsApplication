package ru.statisticsapplication.core.koin

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import ru.statisticsapplication.domain.usecase.GetUsersUseCase
import ru.statisticsapplication.domain.usecase.GetStatisticUseCase

val useCaseModule = module {
    factoryOf(::GetStatisticUseCase)
    factoryOf(::GetUsersUseCase)
}