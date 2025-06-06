package ru.statisticsapplication.presentation.ui.statistic_screen.common.blocks

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.statisticsapplication.R
import ru.statisticsapplication.presentation.logic.model.mvi.StatisticsUiEvent
import ru.statisticsapplication.presentation.logic.model.mvi.StatisticsUiState
import ru.statisticsapplication.presentation.ui.statistic_screen.common.canvas.AgeLineDiagram
import ru.statisticsapplication.presentation.ui.statistic_screen.common.canvas.GenderRoundDiagram
import ru.statisticsapplication.presentation.ui.statistic_screen.common.items.ChipItem

@Composable
internal fun GenderAndAgeBlock(
    uiState: StatisticsUiState,
    events: (StatisticsUiEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        Text(
            modifier = Modifier.padding(
                bottom = 12.dp
            ),
            style = MaterialTheme.typography.titleMedium,
            text = stringResource(R.string.gender_and_age)
        )

        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(bottom = 12.dp)
        ) {
            uiState.chipsGenderAndAge.forEachIndexed { index, chip ->
                ChipItem(
                    chipModelUI = chip
                ) {
                    events(StatisticsUiEvent.ClickOnChipTimeGenderAndAge(chip.type))
                }
                if (index + 1 != uiState.chipsGenderAndAge.size) Spacer(Modifier.width(8.dp))
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(bottom = 20.dp)
        ) {
            GenderRoundDiagram(
                modifier = Modifier.padding(
                    top = 20.dp,
                    start = 40.dp,
                    end = 22.dp
                ),
                menCount = uiState.menCount,
                womenCount = uiState.womenCount
            )

            HorizontalDivider(
                color = MaterialTheme.colorScheme.secondaryContainer,
                modifier = Modifier.padding(vertical = 20.dp)
            )

            uiState.ageStatistics.forEachIndexed { index, ageDiagramModel ->
                AgeLineDiagram(ageDiagramModel)
                if (index + 1 != uiState.ageStatistics.size) Spacer(Modifier.height(12.dp))
            }
        }
    }
}