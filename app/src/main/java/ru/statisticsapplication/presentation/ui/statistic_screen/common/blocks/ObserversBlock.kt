package ru.statisticsapplication.presentation.ui.statistic_screen.common.blocks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.statisticsapplication.R
import ru.statisticsapplication.presentation.logic.model.mvi.StatisticsUiState
import ru.statisticsapplication.presentation.ui.statistic_screen.common.components.DiagramRow
import ru.statisticsapplication.presentation.ui.theme.StatisticsApplicationTheme

@Composable
internal fun ObserversBlock(
    uiState: StatisticsUiState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.padding(bottom = 12.dp),
            style = MaterialTheme.typography.titleMedium,
            text = stringResource(R.string.specter)
        )

        Column(
            modifier = Modifier.background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(16.dp)
            )
        ) {
            DiagramRow(
                count = uiState.countSubscribe,
                message = stringResource(R.string.subscribers),
                isPositive = true
            )
            HorizontalDivider(
                color = MaterialTheme.colorScheme.secondaryContainer
            )

            DiagramRow(
                count = uiState.countUnsubscribe,
                message = stringResource(R.string.unsubscribers),
                isPositive = false
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    StatisticsApplicationTheme {
        ObserversBlock(StatisticsUiState.getDefault())
    }
}