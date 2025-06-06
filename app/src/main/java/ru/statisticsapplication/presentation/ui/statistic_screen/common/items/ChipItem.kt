package ru.statisticsapplication.presentation.ui.statistic_screen.common.items

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.statisticsapplication.presentation.logic.model.ui.ChipModelUI
import ru.statisticsapplication.presentation.logic.model.ui.ChipTypeUI
import ru.statisticsapplication.presentation.ui.theme.StatisticsApplicationTheme

@Composable
internal fun ChipItem(
    chipModelUI: ChipModelUI,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .background(
                color = if (chipModelUI.isSelected) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(49.dp)
            )
            .border(
                width = 1.dp,
                color = if (chipModelUI.isSelected) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSecondaryContainer,
                shape = RoundedCornerShape(49.dp)
            )
            .clickable {
                onClick()
            }
    ) {
        Text(
            modifier = Modifier
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                ),
            text = chipModelUI.name,
            style = MaterialTheme.typography.bodyMedium,
            color = if (chipModelUI.isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Preview
@Composable
private fun Preview() {
    StatisticsApplicationTheme {
        Column {
            ChipItem(
                chipModelUI = ChipModelUI(
                    name = "Сегодня",
                    isSelected = true,
                    type = ChipTypeUI.DAY
                ),
                onClick = {}
            )

            Spacer(Modifier.height(16.dp))

            ChipItem(
                chipModelUI = ChipModelUI(
                    name = "Сегодня",
                    isSelected = false,
                    type = ChipTypeUI.DAY
                ),
                onClick = {}
            )
        }
    }
}