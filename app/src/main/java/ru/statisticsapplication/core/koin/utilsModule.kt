package ru.statisticsapplication.core.koin

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import ru.statisticsapplication.presentation.logic.utils.DateFormatter
import ru.statisticsapplication.presentation.logic.utils.SortUtils

val utilsModule = module {
    factoryOf(::DateFormatter)
    factoryOf(::SortUtils)
}