package ru.statisticsapplication.presentation.ui.statistic_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.statisticsapplication.R
import ru.statisticsapplication.presentation.logic.model.mvi.StatisticsUiEvent
import ru.statisticsapplication.presentation.logic.model.mvi.StatisticsUiState
import ru.statisticsapplication.presentation.ui.statistic_screen.common.blocks.FrequencyOfVisits
import ru.statisticsapplication.presentation.ui.statistic_screen.common.blocks.GenderAndAgeBlock
import ru.statisticsapplication.presentation.ui.statistic_screen.common.blocks.ObserversBlock
import ru.statisticsapplication.presentation.ui.statistic_screen.common.blocks.VisitorsBlock
import ru.statisticsapplication.presentation.ui.theme.StatisticsApplicationTheme

@Composable
internal fun StatisticsContent(
    uiState: StatisticsUiState,
    events: (StatisticsUiEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(Unit) {
        events(StatisticsUiEvent.LoadStatisticsAndUsers)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(
                state = rememberScrollState()
            )
    ) {
        Text(
            modifier = Modifier.padding(
                top = 48.dp,
                start = 16.dp,
                bottom = 32.dp
            ),
            style = MaterialTheme.typography.titleLarge,
            text = stringResource(R.string.statistic)
        )

        VisitorsBlock(
            uiState = uiState,
            events = events,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        FrequencyOfVisits(
            modifier = Modifier.padding(
                horizontal = 16.dp,
                vertical = 28.dp,
            ),
            list = uiState.listUsersFrequencyOfVisits
        )

        GenderAndAgeBlock(
            modifier = Modifier.padding(horizontal = 16.dp),
            events = events,
            uiState = uiState,
        )

        ObserversBlock(
            uiState = uiState,
            modifier = Modifier.padding(
                start = 16.dp,
                end = 16.dp,
                top = 28.dp,
                bottom = 32.dp
            )
        )
    }
}

@Preview
@Composable
private fun Preview() {
    StatisticsApplicationTheme() {
        StatisticsContent(
            events = {},
            uiState = StatisticsUiState.getDefault()
        )
    }
}

