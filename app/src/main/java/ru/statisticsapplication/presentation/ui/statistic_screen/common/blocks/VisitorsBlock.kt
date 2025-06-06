package ru.statisticsapplication.presentation.ui.statistic_screen.common.blocks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.statisticsapplication.R
import ru.statisticsapplication.presentation.logic.model.mvi.StatisticsUiEvent
import ru.statisticsapplication.presentation.logic.model.mvi.StatisticsUiState
import ru.statisticsapplication.presentation.ui.statistic_screen.common.components.DiagramRow

@Composable
internal fun VisitorsBlock(
    uiState: StatisticsUiState,
    events: (StatisticsUiEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.padding(bottom = 12.dp),
            style = MaterialTheme.typography.titleMedium,
            text = stringResource(R.string.visitors)
        )
        DiagramRow(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = RoundedCornerShape(16.dp)
                ),
            count = uiState.countNewViews,
            message = stringResource(R.string.count_visitors),
            isPositive = true
        )

        Spacer(Modifier.height(28.dp))

        DiagramVisitorsBlock(
            uiState = uiState,
            events = events
        )
    }
}
