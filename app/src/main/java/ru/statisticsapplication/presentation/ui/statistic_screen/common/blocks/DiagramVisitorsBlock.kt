package ru.statisticsapplication.presentation.ui.statistic_screen.common.blocks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.statisticsapplication.presentation.logic.model.mvi.StatisticsUiEvent
import ru.statisticsapplication.presentation.logic.model.mvi.StatisticsUiState
import ru.statisticsapplication.presentation.ui.statistic_screen.common.canvas.VisitorsDiagram
import ru.statisticsapplication.presentation.ui.statistic_screen.common.items.ChipItem
import ru.statisticsapplication.presentation.ui.theme.StatisticsApplicationTheme

@Composable
internal fun DiagramVisitorsBlock(
    uiState: StatisticsUiState,
    events: (StatisticsUiEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
    ) {
        uiState.chipsVisitorsTime.forEach { chip ->
            ChipItem(
                chipModelUI = chip
            ) {
                events(StatisticsUiEvent.ClickOnChipVisitorsTime(chip.type))
            }
        }
    }
    VisitorsDiagram(
        date = uiState.listRoundVisitors
    )
}

@Preview
@Composable
private fun Preview() {
    StatisticsApplicationTheme {
        Column(Modifier.background(MaterialTheme.colorScheme.background)) {
            DiagramVisitorsBlock(
                uiState = StatisticsUiState.getDefault(),
                events = {}
            )
        }
    }
}