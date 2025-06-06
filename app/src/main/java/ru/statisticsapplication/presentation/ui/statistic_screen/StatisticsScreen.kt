package ru.statisticsapplication.presentation.ui.statistic_screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel
import ru.statisticsapplication.presentation.logic.viewmodel.StatisticsViewModel

@Composable
fun StatisticsScreen(
    viewModel: StatisticsViewModel = koinViewModel()
) {
    val state = viewModel.state.collectAsState()
    val events = viewModel::sendEvent

    Scaffold { paddings ->
        StatisticsContent(
            uiState = state.value,
            events = events,
            modifier = Modifier.padding(paddings)
        )
    }
}