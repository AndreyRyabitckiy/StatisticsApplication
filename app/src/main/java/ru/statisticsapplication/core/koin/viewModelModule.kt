package ru.statisticsapplication.core.koin

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import ru.statisticsapplication.presentation.logic.viewmodel.StatisticsViewModel

val viewModelModule = module {
    viewModelOf(::StatisticsViewModel)
}